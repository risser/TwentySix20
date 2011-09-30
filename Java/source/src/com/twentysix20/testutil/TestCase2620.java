package com.twentysix20.testutil;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;
import java.util.Hashtable;

import junit.framework.TestCase;

public class TestCase2620 extends TestCase {

    public TestCase2620() {
        super();
        // TODO Auto-generated constructor stub
    }

    public TestCase2620(String arg0) {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    public static void assertEquals(String a, String b) {
        String failure = "Expected <"+a+"> but was <"+b+">.  ";
        if (a == null) 
            assertNull(failure, b);
        else
            assertTrue(failure, a.equals(b));
    }

    public static void assertEquals(BigDecimal a, BigDecimal b) {
        String failure = "Expected <"+a+"> but was <"+b+">.  ";
        if (a == null) 
            assertNull(failure, b);
        else {
            assertTrue(failure, a.compareTo(b) == 0);
        }
    }

    public static void assertEquals(String expected, BigDecimal big) {
        String failure = "Expected <"+expected+"> but was <"+big+">.  ";
        if (expected == null) 
            assertNull(failure, big);
        else {
            assertTrue(failure, new BigDecimal(expected).compareTo(big) == 0);
        }
    }

    public static void assertExactlyEquals(BigDecimal a, BigDecimal b) {
    	assertEquals((Object)a, (Object)b);
    }

    public static void assertExactlyEquals(String expected, BigDecimal b) {
    	assertEquals((Object)new BigDecimal(expected), (Object)b);
    }

    public static void assertEquals(String expectedValue, Date actualDate) {
        if (expectedValue == null) 
            assertEquals((Date)null, actualDate);
        else {
            Date expectedDate = new Date(expectedValue);
            assertEquals(expectedDate, actualDate);
        }
    }

    public static void assertEqualsCharByChar(String expectedValue, String actualValue) {
        if (expectedValue == null) {
            if (actualValue != null)
                fail("Expected Value is null, but Actual Value was '"+actualValue+"'.");
        } else if (actualValue == null) {
            fail("Expected Value is '"+expectedValue+"', but Actual Value was null.");
        } else if (!expectedValue.equals(actualValue)) {
            int shortestLength = (actualValue.length() < expectedValue.length() ? actualValue.length() : expectedValue.length());
            int onChar = -1;
            String message = "Strings are not equal.  They differ at:";
            for (int i = 0; i < shortestLength; i++) {
                char actChar = actualValue.charAt(i);
                char expChar = expectedValue.charAt(i);
                if (actChar != expChar) {
                    if (onChar == -1)
                        onChar = i;
                } else { // they are equal
                    if (onChar != -1)
                        message += generatePosMessage(expectedValue, actualValue, onChar, i);
                    onChar = -1;
                }
            }
            if (onChar > -1)
                message += generatePosMessage(expectedValue, actualValue, onChar, shortestLength);
            if (actualValue.length() > shortestLength)
                message += "\n  position "+(shortestLength+1)+"-"+(actualValue.length())+": expected nothing, but was '"+actualValue.substring(shortestLength)+"'.";
            if (expectedValue.length() > shortestLength)
                message += "\n  position "+(shortestLength+1)+"-"+(expectedValue.length())+": expected '"+expectedValue.substring(shortestLength)+"', but was nothing.";
            fail(message);
        }
    }

    private static String generatePosMessage(String expectedValue, String actualValue, int first, int last) {
        String actStr = actualValue.substring(first,last);
        String expStr = expectedValue.substring(first,last);
        first += 1;
        return "\n  position "+first+(last == first ? "" : "-"+last)+": expected '"+expStr+"', but was '"+actStr+"'.";
    }

    public static void assertFalse(Object o) {
        String failure = "Boolean should have been <false>, but was <"+o+">.  ";
        assertTrue(failure, Boolean.FALSE.equals(o));
    }
    
    public static void assertTrue(Object o) {
        String failure = "Boolean should have been <true>, but was <"+o+">.  ";
        assertTrue(failure, Boolean.TRUE.equals(o));
    }

    public static void assertInstanceOf(Object o, Class c) {
        if (o == null) fail("Expected class <"+c.getName()+">; Actual class <null>");
        if (c == null) fail("Can't pass null for the class in assertInstanceOf.");
        assertTrue("\nExpected class <"+c.getName()+">\nActual class <"+o.getClass().getName()+">",c.isAssignableFrom(o.getClass()));
    }

    public static void assertSize(int number, Collection coll) {
        String expected = "Expected <"+number+"> item"+(number == 1 ? "" : "s");
        if (coll == null) fail(expected+", but collection was null.");
        assertEquals("Mismatched sizes.  "+expected+", but Collection had <"+coll.size()+"> :: ",number,coll.size());
    }

    public static void assertSize(int number, Hashtable ht) {
        String expected = "Expected <"+number+"> item"+(number == 1 ? "" : "s");
        if (ht == null) fail(expected+", but collection was null.");
        assertEquals("Mismatched sizes.  "+expected+", but Collection had <"+ht.size()+"> :: ",number,ht.size());
    }
}
