/*
 * TestStringUtil.java
 *
 * Created on September 29, 2004, 10:14 AM
 */

package com.twentysix20.util;

import junit.framework.TestCase;

import com.twentysix20.util.StringUtil;

/**
 *
 * @author  tpnr007
 */
public class TestStringUtil extends TestCase {
    
    public static void main(String args[]) {
		junit.textui.TestRunner.run(TestStringUtil.class);
	}

    public void testIsEmpty_null() {
        assertTrue(StringUtil.isEmpty(null));
    }
    
    public void testIsEmpty_not() {
        assertFalse(StringUtil.isEmpty("blah"));
        assertFalse(StringUtil.isEmpty("blah   "));
        assertFalse(StringUtil.isEmpty("    blah"));
        assertFalse(StringUtil.isEmpty("    .   "));
        assertFalse(StringUtil.isEmpty("\t\n\t**   \n\t\n"));
    }
    
    public void testIsEmpty_whitespace() {
        assertTrue(StringUtil.isEmpty(""));
        assertTrue(StringUtil.isEmpty(" "));
        assertTrue(StringUtil.isEmpty("\n"));
        assertTrue(StringUtil.isEmpty("   \n    "));
        assertTrue(StringUtil.isEmpty("\t\n\t   \n\t\n"));
    }
    
    public void testContains_error() {
        try {
            StringUtil.contains(null,"test");
            fail("Should have thrown exception: no null");
        } catch (IllegalArgumentException iae) {}            
    }
    
    public void testContains() {
        assertTrue(StringUtil.contains("This is a test"," "));
        assertTrue(StringUtil.contains("This is a test"," a "));
        assertFalse(StringUtil.contains("This is a test","nope"));
        assertTrue(StringUtil.contains("This is a test",""));
        assertTrue(StringUtil.contains("",""));
        assertFalse(StringUtil.contains(""," "));
    }
    
    public void testGrab_error() {
        try {
            StringUtil.grab(null,"test","me");
            fail("Should have thrown exception: no null");
        } catch (IllegalArgumentException iae) {}            
        try {
            StringUtil.grab("This is a test",null,"me");
            fail("Should have thrown exception: no null");
        } catch (IllegalArgumentException iae) {}            
        try {
            StringUtil.grab("This is a test","test",null);
            fail("Should have thrown exception: no null");
        } catch (IllegalArgumentException iae) {}     
    }
    
    public void testGrab_bad() {
        assertNull(StringUtil.grab("This is a test","is","nope"));
        assertNull(StringUtil.grab("This is a test","nope","test"));
        assertNull(StringUtil.grab("This is a test","test","is"));
        assertNull(StringUtil.grab("Here is a test","is "," a"));
    }
    
    public void testGrab_normal() {
        assertEquals(" is a ",StringUtil.grab("This is a test","This","test"));
        assertEquals("is",StringUtil.grab("This is a test"," "," "));
        assertEquals("is a test",StringUtil.grab("This is a test"," ",""));
        assertEquals("This",StringUtil.grab("This is a test",""," "));
        assertEquals("This is a test",StringUtil.grab("This is a test","",""));
        assertEquals(" ",StringUtil.grab("This is a test","is","is"));
        assertEquals("",StringUtil.grab("This is a test","is"," "));
    }
    
    public void testGrab_twoParam() {
        assertEquals(" is a test",StringUtil.grab("This is a test","This"));
        assertEquals("is a test",StringUtil.grab("This is a test"," "));
        assertEquals("This is a test",StringUtil.grab("This is a test",""));
        assertEquals(" test",StringUtil.grab("This is a test","a"));
    }
}
