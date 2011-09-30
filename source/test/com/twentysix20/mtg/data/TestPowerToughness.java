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
* Copyright (c) 2006 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Mar 28, 2006
*/
package com.twentysix20.mtg.data;

import junit.framework.TestCase;

import com.twentysix20.mtg.data.pt.PowerToughness;

public class TestPowerToughness extends TestCase {

    public void testGoodPT() {
        PowerToughness pt;
        pt = new PowerToughness("0/0");
        pt = new PowerToughness("1/1");
        pt = new PowerToughness("0/1");
        pt = new PowerToughness("1/4");
        pt = new PowerToughness("3/2");
        pt = new PowerToughness("11/11");
        pt = new PowerToughness("*/7-*");
        pt = new PowerToughness("*/*");
        pt = new PowerToughness("*/1+*");
        pt = new PowerToughness("3/*");
        pt = new PowerToughness("3/1+*");
        pt = new PowerToughness("1+*/1+*");
        pt = new PowerToughness("1+*/4");
        pt = new PowerToughness("*/4");
        pt = new PowerToughness("99/99");
    }

    public void testGoodPT2() {
        PowerToughness pt;
        pt = new PowerToughness("0","0");
        pt = new PowerToughness("1","1");
        pt = new PowerToughness("0","1");
        pt = new PowerToughness("1","4");
        pt = new PowerToughness("3","2");
        pt = new PowerToughness("11","11");
        pt = new PowerToughness("*","7-*");
        pt = new PowerToughness("*","*");
        pt = new PowerToughness("*","1+*");
        pt = new PowerToughness("3","*");
        pt = new PowerToughness("3","1+*");
        pt = new PowerToughness("1+*","1+*");
        pt = new PowerToughness("1+*","4");
        pt = new PowerToughness("*","4");
        pt = new PowerToughness("99","99");
    }
    
    public void testBadPT() {
        tryPT("");
        tryPT("*");
        tryPT("X/X");
        tryPT("*/*-1");
        tryPT("","*");
        tryPT("*","");
        tryPT("X","X");
        tryPT("*","*-1");
    }
    
    public void testEquals() {
        assertEquals(new PowerToughness("0","0"), new PowerToughness("0/0"));
        assertEquals(new PowerToughness("1","1"), new PowerToughness("1/1"));
        assertEquals(new PowerToughness("0","1"), new PowerToughness("0/1"));
        assertEquals(new PowerToughness("1","4"), new PowerToughness("1/4"));
        assertEquals(new PowerToughness("3","2"), new PowerToughness("3/2"));
        assertEquals(new PowerToughness("11","11"), new PowerToughness("11/11"));
        assertEquals(new PowerToughness("*","7-*"), new PowerToughness("*/7-*"));
        assertEquals(new PowerToughness("*","*"), new PowerToughness("*/*"));
        assertEquals(new PowerToughness("*","1+*"), new PowerToughness("*/1+*"));
        assertEquals(new PowerToughness("3","*"), new PowerToughness("3/*"));
        assertEquals(new PowerToughness("3","1+*"), new PowerToughness("3/1+*"));
        assertEquals(new PowerToughness("1+*","1+*"), new PowerToughness("1+*/1+*"));
        assertEquals(new PowerToughness("1+*","4"), new PowerToughness("1+*/4"));
        assertEquals(new PowerToughness("*","4"), new PowerToughness("*/4"));
        assertEquals(new PowerToughness("99","99"), new PowerToughness("99/99"));
    }
    
    public void testToString() {
        assertEquals("0/0", new PowerToughness("0","0").toString());
        assertEquals("1/1", new PowerToughness("1","1").toString());
        assertEquals("0/1", new PowerToughness("0","1").toString());
        assertEquals("1/4", new PowerToughness("1","4").toString());
        assertEquals("3/2", new PowerToughness("3","2").toString());
        assertEquals("11/11", new PowerToughness("11","11").toString());
        assertEquals("*/7-*", new PowerToughness("*","7-*").toString());
        assertEquals("*/*", new PowerToughness("*","*").toString());
        assertEquals("*/1+*", new PowerToughness("*","1+*").toString());
        assertEquals("3/*", new PowerToughness("3","*").toString());
        assertEquals("3/1+*", new PowerToughness("3","1+*").toString());
        assertEquals("1+*/1+*", new PowerToughness("1+*","1+*").toString());
        assertEquals("1+*/4", new PowerToughness("1+*","4").toString());
        assertEquals("*/4", new PowerToughness("*","4").toString());
        assertEquals("99/99", new PowerToughness("99","99").toString());
    }
    
    private void tryPT(String pt) {
        try {
            PowerToughness x = new PowerToughness(pt);
            fail("Should have thrown IllegalArgumentException."); 
        } catch (IllegalArgumentException e) {
            // good 
        }
    }
    
    private void tryPT(String p, String t) {
        try {
            PowerToughness x = new PowerToughness(p,t);
            fail("Should have thrown IllegalArgumentException."); 
        } catch (IllegalArgumentException e) {
            // good 
        }
    }

}
