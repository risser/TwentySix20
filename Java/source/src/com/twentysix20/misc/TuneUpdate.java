package com.twentysix20.misc;

/*
 * TuneUpdate.java
 *
 * Created on October 25, 2004, 1:14 PM
 */

import java.io.*;
import java.util.*;

/**
 *
 * @author  Peter Risser
 */
public class TuneUpdate {

    private HashSet playlist = new HashSet();
    private HashSet ignore = new HashSet();
    private HashSet files = new HashSet();

    private static int baubleCt = 0;
    private static String bauble = "|/-\\";
    private static int next() { return (baubleCt++ % bauble.length()); }
    private static void baublePrint() { System.out.print("\u0008"+bauble.charAt(next())); }
    
    public TuneUpdate() {}

    public void readPlayList(String fileName) throws IOException {
        if (fileName.charAt(1) != ':') throw new IllegalArgumentException("Playlist filename must include drive letter. ("+fileName+")");
        String drive = fileName.substring(0,1);
        BufferedReader rd = new BufferedReader(new FileReader(fileName));
        String listpath = fileName.substring(0,fileName.lastIndexOf('\\'));
        String line = "";
        int lineCt = 0;
        while ((line = rd.readLine()) != null) {
            lineCt++;
            baublePrint();
            if ("".equals(line.trim())) {
                System.out.println(" *** WARNING: Line "+lineCt+" is blank.");
                continue;
            }
            if (!line.startsWith("#")) {
                if ((line.charAt(0) != '\\') && (line.charAt(1) != ':')) {
                    line = listpath + "\\" + line;
                } else if (line.charAt(1) != ':') {
                    line = drive + ":" + line;
                }
                line = new File(line).getCanonicalPath();
                if (playlist.contains(line))
                    System.out.println("\n>> Duplicate: "+line);
                playlist.add(line);
            }
        }
        rd.close();
    }
    
    public void ignore(String directory) {
        ignore.add(directory);
    }

    public void scanFiles(String start) throws IOException {
        File starter = new File(start);
        files = scan(starter);
    }
    
    private HashSet scan(File startFile) throws IOException {
        baublePrint();
        if (!startFile.isDirectory()) throw new IllegalArgumentException(startFile.getName() + " is not a directory.");
        HashSet localFiles = new HashSet();
        File[] filess = startFile.listFiles();
        if (filess != null) {
            for (int i = 0; i < filess.length; i++) {
                if (!ignore.contains(filess[i].getCanonicalPath())) {
                    if (filess[i].getName().toUpperCase().endsWith(".MP3")) {
                        localFiles.add(filess[i].getCanonicalPath());
                    } else if (filess[i].isDirectory()) {
                        Set theFiles = scan(filess[i]);
                        if (theFiles.size() == 0) System.out.println("*** GONE: " + filess[i]);
                        if (theFiles.size() == 1) System.out.println("uno: " + filess[i]);
                        localFiles.addAll(theFiles);
                    }
                }
            }
        } 
        return localFiles;
    }
    
    public Set determineDeleted() {
        TreeSet delete = new TreeSet();
        TreeSet missing = new TreeSet();
        delete.addAll(files);
        Iterator plays = playlist.iterator();
        while (plays.hasNext()) {
            Object play = plays.next();
            if (delete.contains(play))
                delete.remove(play);
            else
                missing.add(play);
        }
        System.out.println();
        Iterator dudes = missing.iterator();
        while (dudes.hasNext()) 
            System.out.println(">> Missing: "+dudes.next());

        return delete;
//        delete.removeAll(playlist);
    }
    
    public void killEmAll(Set delete) {
        Iterator d = delete.iterator();
        while (d.hasNext()) {
            String name = d.next().toString();
            File killMe = new File(name);
            if (!killMe.delete())
                System.out.println("*** Problem: "+name);
            else
                System.out.println("Deleted: "+name);
        }
    }

    public void showEmAll(Set delete) {
        Iterator d = delete.iterator();
        while (d.hasNext()) {
            String name = d.next().toString();
            File killMe = new File(name);
            System.out.println("On the Block: " + name);
        }
    }


	public static void main(String[] args) throws Exception {
        TuneUpdate td = new TuneUpdate();

        td.ignore("C:\\Risser\\Tunes\\_IN BOX");
        td.ignore("C:\\Risser\\Tunes\\_OUT BOX");
        td.ignore("C:\\Risser\\Tunes\\_NEEDS CLEANED");

        td.readPlayList("C:\\Risser\\XX.m3u");
        td.scanFiles("C:\\Risser\\Tunes\\X");
        Set delete = td.determineDeleted();

        System.out.println("\n\n");
        td.showEmAll(delete);

        if (delete.size() < 1) {
            System.out.println("Nothing to delete.");
        } else {
            System.out.println("Shall we delete these "+delete.size()+" files? (YES to delete): ");
            byte[] input = new byte[3];
            System.in.read(input);
            String bob = new String(input);
            if ("YES".equals(bob))
                td.killEmAll(delete);
            else
                System.out.println("\nNothing deleted.");
        }

        System.out.println("\nDone");
    }
}
