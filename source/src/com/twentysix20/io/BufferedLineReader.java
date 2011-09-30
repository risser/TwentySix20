/*
 * BufferedLineReader.java
 *
 * Created on September 5, 2004, 8:46 PM
 */

package com.twentysix20.io;
import java.io.*;

/**
 *
 * @author  Peter Risser
 */
public class BufferedLineReader {
    BufferedReader input;
    String currentLine = null;
    boolean allDone = false;
    boolean started = false;

    public BufferedLineReader(String filename) throws IOException {
        input = new BufferedReader(new FileReader(filename));
    }

    /**
     * Initializes reader, loads next line; returns true if the line is read, false if no more lines to read.
     */
    public String nextLine() throws IOException {
        started = true;
        if (!allDone)
            currentLine = input.readLine();
        if ((currentLine == null) && !allDone)
            input.close();
        allDone = (currentLine == null);
        return currentLine;
    }
    
    public boolean eof() {
        if (!started) throw new IllegalStateException("Must call nextLine before determining end of file.");
        return allDone;
    }

    /**
     * Returns loaded line.
     */
    public String line() {
        if (!started) throw new IllegalStateException("Must call nextLine before reading the line.");
        return (allDone ? null : currentLine);
    }
}