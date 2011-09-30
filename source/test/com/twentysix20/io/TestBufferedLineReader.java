package com.twentysix20.io;

import com.twentysix20.io.BufferedLineReader;
import com.twentysix20.util.*;
import junit.framework.*;
import com.twentysix20.util.exception.*;
import java.util.*;
import java.io.*;

public class TestBufferedLineReader extends TestCase {
    
    public static String TEST_FILE_DIRECTORY = "c:/Risser/My Dropbox/Projects/Java/source/test/com/twentysix20/io/";

    public static void main(String args[]) {
		junit.textui.TestRunner.run(TestBufferedLineReader.class);
	}
    
    public void testEarlyRead() throws Exception {
        BufferedLineReader reader = new BufferedLineReader(TEST_FILE_DIRECTORY+"smalltestfile.txt");
        try {
            String s = reader.line();
            fail ("Should have thrown IllegalStateException: can't read before nextLine");
        } catch(IllegalStateException ise) {}
        try {
            boolean b = reader.eof();
            fail ("Should have thrown IllegalStateException: can't read before nextLine");
        } catch(IllegalStateException ise) {}
    }
    
    public void testEmptyFile() throws Exception {
        BufferedLineReader reader = new BufferedLineReader(TEST_FILE_DIRECTORY+"empty.txt");
        reader.nextLine();
        assertTrue(reader.eof());
        assertNull(reader.line());
        reader.nextLine();
        assertTrue(reader.eof());
        assertNull(reader.line());
    }
    public void testFileNotFound() throws Exception {
        try {
            BufferedLineReader reader = new BufferedLineReader(TEST_FILE_DIRECTORY+"notfound.txt");
            fail("Should throw file not found.");
        } catch (FileNotFoundException fnfe) {}
    }
    public void testFileRead() throws Exception {
        BufferedLineReader reader = new BufferedLineReader(TEST_FILE_DIRECTORY+"smalltestfile.txt");
        reader.nextLine();
        assertFalse(reader.eof());
        assertEquals("first line",reader.line());
        reader.nextLine();
        assertFalse(reader.eof());
        assertEquals("second line",reader.line());
        reader.nextLine();
        assertFalse(reader.eof());
        assertEquals("",reader.line());
        reader.nextLine();
        assertFalse(reader.eof());
        assertEquals("last line",reader.line());
        reader.nextLine();
        assertTrue(reader.eof());
        assertNull(reader.line());
    }
}
