package com.twentysix20.misc;

/*
 * RSSChecker.java
 *
 * Created on March 4, 2005, 10:43 AM
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 *
 * @author  tpnr007
 */
public class RSSChecker extends Thread {
    private String urlStr;
    private String path;
    private int minutes;
    private URL url;
    private long millis;
    
    private boolean quitit = false;

    
    /** Creates a new instance of RSSChecker */
    public RSSChecker(String url, String path, int minutes) throws java.net.MalformedURLException {
        this.urlStr = url;
        this.path = path;
        this.minutes = minutes;
        this.url = new URL(urlStr);
        this.millis = minutes * 60 * 1000;
    }
    
    public String grabit() throws java.io.IOException {
System.out.println("Grabbing: "+new Date());        
        URLConnection connection = url.openConnection();
        connection.connect();
        BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line = "";
        StringBuffer buff = new StringBuffer();
        boolean firstDateSkipped = false;
        while ((line = rd.readLine()) != null) {
            line = line.trim();
            if (!firstDateSkipped && line.startsWith("<dc:date>")) {
                firstDateSkipped = true;
                continue;
            }
            if (!line.startsWith("<guid"))
                if (line.startsWith("<rdf:RDF "))
                    buff.append("<rdf:RDF ");
                else
                    buff.append(line).append("\n");
        }
        rd.close();
        return buff.toString();
    }
    
    public void writeit(String it) throws java.io.IOException {
System.out.println("Writing: "+new Date()+"\n");
        String fileName = path + "\\recent."+(new Date()).getTime()+".xml";
        BufferedWriter out = new BufferedWriter(new FileWriter(fileName));
        out.write(it);
        out.close();
    }
    
    public void run() {
System.out.println("Running: "+new Date());        
        String lastContent = "";
        while (!quitit) { // love is forever, baby
            String content = null;
            try {
                content = grabit();
            } catch (Exception e) {
                System.out.println("Whoops.  Exception: "+e);
            }
            if ((content != null) && !content.equals(lastContent)) {
                try {
                    writeit(content);
                } catch (java.io.IOException ioe) {
                    throw new RuntimeException(ioe);
                }
            }
            lastContent = content;
            try {
                sleep(millis);
            } catch (InterruptedException ie) {
                System.out.println("Interrupted(?): "+new Date());
            }
        }
System.out.println("Stopped: "+new Date());        
    }
    
    public void knockItOff() {
        quitit = true;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
//        RSSChecker checker = new RSSChecker("http://ws.audioscrobbler.com/rss/recent.php?user=risser", 
        RSSChecker checker = new RSSChecker("http://ws.audioscrobbler.com/rdf/history/risser", 
                                            "C:\\Risser\\Music\\RecentTrax\\", 8);
        checker.start();
        byte[] input = new byte[1];
        System.in.read(input);
        checker.knockItOff();
        checker.interrupt();
System.out.println("All Done.");
    }
}
