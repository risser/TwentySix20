/******************************************************************************
*
* Proprietary and Confidential
* Ohio Farmers Insurance Company
*
* This document contains material which is the proprietary and confidential
* property of Ohio Farmers Insurance Company.
*
* The right to view, reproduce, modify, distribute, or in any way display
* this work is prohibited without the express written consent of the Ohio
* Farmers Insurance Company.
*
* Copyright (c) 2007 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Jun 18, 2007
*/
package com.twentysix20.lolcode;

import com.twentysix20.lolcode.exception.MissingNextLineException;
import com.twentysix20.lolcode.exception.UnterminatedStringException;
import com.twentysix20.testutil.TestCase2620;

public class CompilerTestCase extends TestCase2620 {
    public void testEmptyString() {
        Compiler c = new Compiler("");
        assertEquals(1, c.getLines().length);
        assertInstanceOf(c.getProgram(), Program.class);
    }

    public void testEmptyArray() {
        Compiler c = new Compiler(new String[]{});
        assertEquals(0, c.getLines().length);
        assertInstanceOf(c.getProgram(), Program.class);
    }

    public void testLineBreak() {
        Compiler c = new Compiler("\n");
        assertEquals(1, c.getLines().length);
        assertEquals("", c.getLines()[0]);
        assertInstanceOf(c.getProgram(), Program.class);
    }

    public void testTwoLineBreaks() {
        Compiler c = new Compiler("\n\n");
        assertEquals(2, c.getLines().length);
        assertEquals("", c.getLines()[0]);
        assertEquals("", c.getLines()[1]);
        assertInstanceOf(c.getProgram(), Program.class);
    }

    public void testNull() {
        String s = null;
        Compiler c1 = new Compiler(s);
        assertEquals(1, c1.getLines().length);
        assertEquals("", c1.getLines()[0]);

        String[] ss = null;
        Compiler c2 = new Compiler(ss);
        assertEquals(1, c2.getLines().length);
        assertEquals("", c2.getLines()[0]);
    }

    public void testOddQuotes() {
        Compiler c1 = new Compiler("VISIBLE \"THIS IS OKAY\"");
        Compiler c2 = new Compiler("VISIBLE \"THIS IS OKAY\" TOO");
        Compiler c3 = new Compiler("VISIBLE \"THIS IS :\"OKAY:\"");
        Compiler c7 = new Compiler("VISIBLE \"THIS IS \" ...     \"OKAY\"");
        Compiler c8 = new Compiler("VISIBLE \"THIS IS ... OKAY TOO\"");

//        try {
//            Compiler c4 = new Compiler("VISIBLE \"THIS IS NOT OKAY");
//            fail("Should be Unterminated String Exception");
//        } catch (UnterminatedStringException e) {
//            // fine
//        }
//        try {
//            Compiler c5 = new Compiler("VISIBLE \"THIS IS NOT OKAY\" EITHER\"");
//        } catch (UnterminatedStringException e) {
//            // fine
//        }
//        try {
//            Compiler c6 = new Compiler("VISIBLE \"THIS IS NOT OKAY\" EITHER\" N STUFF");
//        } catch (UnterminatedStringException e) {
//            // fine
//        }
//
//        try {
//            Compiler c9 = new Compiler("VISIBLE \"THIS IS NOT OKAY ...");
//            fail("Should be Unterminated String Exception");
//        } catch (UnterminatedStringException e) {
//            // fine
//        }
    }

    public void testContinueOnce() {
        Compiler c = new Compiler("VISIBLE THIS ...\n   THAT OTHER\nVISIBLE TWO");
        assertEquals(3, c.getLines().length);
        assertEquals("VISIBLE THIS THAT OTHER", c.getLines()[0]);
        assertEquals("", c.getLines()[1]);
        assertEquals("VISIBLE TWO", c.getLines()[2]);
    }

    public void testContinueTwice() {
        Compiler c = new Compiler("VISIBLE THIS N ...\n   THAT N ...\n     TEH OTHER\nVISIBLE TWO");
        assertEquals(4, c.getLines().length);
        assertEquals("VISIBLE THIS N THAT N TEH OTHER", c.getLines()[0]);
        assertEquals("", c.getLines()[1]);
        assertEquals("", c.getLines()[2]);
        assertEquals("VISIBLE TWO", c.getLines()[3]);
    }

    public void testContinueTwiceWithBlank() {
        Compiler c = new Compiler("VISIBLE THIS N ...\n    ...\n     TEH OTHER\nVISIBLE TWO");
        assertEquals(4, c.getLines().length);
        assertEquals("VISIBLE THIS N TEH OTHER", c.getLines()[0]);
        assertEquals("", c.getLines()[1]);
        assertEquals("", c.getLines()[2]);
        assertEquals("VISIBLE TWO", c.getLines()[3]);
    }

    public void testContinueBad() {
        try {
            Compiler c = new Compiler("VISIBLE THIS N ...");
            fail("Should have thrown Exception");
        } catch (MissingNextLineException e) {
            // fine
        }
        try {
            Compiler c = new Compiler("VISIBLE THIS N ...\n");
            fail("Should have thrown Exception");
        } catch (MissingNextLineException e) {
            // fine
        }
        try {
            Compiler c = new Compiler("VISIBLE THIS N ...\n      \n     OTHER THING");
            fail("Should have thrown Exception");
        } catch (MissingNextLineException e) {
            // fine
        }
    }
/*
    public void testSingleLineComment() {
        Compiler c = new Compiler("VISIBLE THIS N THAT BTW YOU SUCK");
        assertEquals(1, c.getLines().length);
        assertEquals("VISIBLE THIS N THAT", c.getLines()[0]);
    }

    public void testSingleLineCommentWithTab() {
        Compiler c = new Compiler("VISIBLE THIS N THAT\tBTW YOU SUCK");
        assertEquals(1, c.getLines().length);
        assertEquals("VISIBLE THIS N THAT", c.getLines()[0]);
    }

    public void testTwoComments() {
        Compiler c = new Compiler("VISIBLE THIS N THAT BTW YOU SUCK \n VISIBLE THE OTHER BTW UP YOURS");
        assertEquals(2, c.getLines().length);
        assertEquals("VISIBLE THIS N THAT", c.getLines()[0]);
        assertEquals("VISIBLE THE OTHER", c.getLines()[1]);
    }

    public void testCommentNonContinue() {
        Compiler c = new Compiler("VISIBLE THIS N THAT BTW YOU SUCK ...\n VISIBLE THE OTHER");
        assertEquals(2, c.getLines().length);
        assertEquals("VISIBLE THIS N THAT", c.getLines()[0]);
        assertEquals("VISIBLE THE OTHER", c.getLines()[1]);
    }

    public void testNotAComment() {
        Compiler c = new Compiler("VISIBLE THIS N KNOBTWEAKER");
        assertEquals(1, c.getLines().length);
        assertEquals("VISIBLE THIS N KNOBTWEAKER", c.getLines()[0]);
    }

    public void testNotACommentInString() {
        Compiler c = new Compiler("VISIBLE THIS N \"THAT BTW YOU SUCK\"");
        assertEquals(1, c.getLines().length);
        assertEquals("VISIBLE THIS N \"THAT BTW YOU SUCK\"", c.getLines()[0]);
    }

    public void testStringSubs() {
        String s = "VISIBLE \""+Constants.ESCAPED_ESCAPECHAR+Constants.ESCAPED_ESCAPECHAR+
                Constants.ESCAPED_QUOTE+
                Constants.ESCAPED_NEWLINE+
                Constants.ESCAPED_TAB+
                Constants.ESCAPED_BEEP+
                "\"";
        Compiler c = new Compiler(s);
        assertEquals(1, c.getLines().length);
        assertEquals("VISIBLE "+Constants.JAVA_QUOTE+"::\"\t\u0007"+Constants.JAVA_QUOTE, c.getLines()[0]);
//        assertEquals("VISIBLE \"::\"\t\u0007", c.getLines()[0]);
    }
    */
}