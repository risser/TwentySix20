package com.twentysix20.misc;

/*
 * ReadWriteSearcher.java
 *
 * Created on October 6, 2004, 5:25 PM
 */

import java.util.*;
import java.io.*;

/**
 *
 * @author  tpnr007
 */
public class ReadWriteSearcher {
    private static final String STARTING_DIRECTORY = "C:/views/WesCom_PL_5_1_0_Dev"; //WesCom_PL_5.0_Dev"; //PL_R4.5";
    private static int count = 0;
//    private static String bauble = "|/-\\";
//    private static int next() { return (count++ % bauble.length()); }
    private static void blip() { 
        System.out.print(".");
        count = (count + 1) % 100;
        if (count == 0)
            System.out.println();
    }

    protected static Collection gatherFiles(File startFile) {
        blip();
        if (!startFile.isDirectory()) throw new IllegalArgumentException(startFile.getName() + " is not a directory.");
        Set rwFiles = new TreeSet();
        File[] filess = startFile.listFiles();
        if (filess != null) {
            for (int i = 0; i < filess.length; i++) {
                if (filess[i].canWrite() && 
                     isIncludedFile(filess[i]) &&
                     !isExcludedFile(filess[i])) {
                    rwFiles.add(filess[i]);
                } else if (filess[i].isDirectory() && !isExcludedDirectory(filess[i])) {
                    rwFiles.addAll(gatherFiles(filess[i]));
                }
            }
        } 
        return rwFiles;
    }

    private static boolean isExcludedDirectory(File file) {
        return (
                file.getName().equals("jsp_servlet") ||
                file.getName().equals("CUSTOM")
               );
    }

    private static boolean isExcludedFile(File file) {
        return (
                file.getPath().matches(".*delegate.*implementation.*")
               );
    }

    private static boolean isIncludedFile(File file) {
        return (
                file.getName().endsWith(".java") || 
                file.getName().endsWith(".properties") || 
                file.getName().endsWith(".xml") || 
                file.getName().endsWith(".jsp")
               );
    }
    
    public static void main (String[] args) {
        File starter = new File(STARTING_DIRECTORY);
        System.out.println("Scanning:  ");
        Iterator files = gatherFiles(starter).iterator();
        System.out.println("\n");
        
        System.out.println("Files that are read-write:");
        while (files.hasNext()) {
            System.out.println("> "+files.next());
        }
        System.out.println("Done.");
    }
}
