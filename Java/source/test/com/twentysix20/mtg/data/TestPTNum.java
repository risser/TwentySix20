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

import com.twentysix20.mtg.data.pt.PTNum;

public class TestPTNum extends TestCase {
    public void testPosInt() {
        PTNum x = new PTNum("3");
        PTNum x2 = new PTNum(3, false);
        assertEquals(x2,x);
        assertEquals("3",x.toString());
        assertEquals("3",x2.toString());
    }
    public void testNegInt() {
        PTNum x = new PTNum("-1");
        PTNum x2 = new PTNum(-1, false);
        assertEquals(x2,x);
        assertEquals("-1",x.toString());
        assertEquals("-1",x2.toString());
    }
    public void testZero() {
        PTNum x = new PTNum("0");
        PTNum x2 = new PTNum(0, false);
        assertEquals(x2,x);
        assertEquals("0",x.toString());
        assertEquals("0",x2.toString());
    }
    public void testStar() {
        PTNum x = new PTNum("*");
        PTNum x2 = new PTNum(0, true);
        assertEquals(x2,x);
        assertEquals("*",x.toString());
        assertEquals("*",x2.toString());
    }
    public void testPosStar() {
        PTNum x = new PTNum("1+*");
        PTNum x2 = new PTNum(1, true);
        assertEquals(x2,x);
        assertEquals("1+*",x.toString());
        assertEquals("1+*",x2.toString());
    }
    public void testNegStar() {
        PTNum x = new PTNum("7-*");
        PTNum x2 = new PTNum(-7, true);
        assertEquals(x2,x);
        assertEquals("7-*",x.toString());
        assertEquals("7-*",x2.toString());
    }

    public void testBadPT() {
        tryPT("");
    }

    private void tryPT(String pt) {
        try {
            PTNum t = new PTNum(pt);
            fail("Should have thrown IllegalArgumentException."); 
        } catch (IllegalArgumentException e) {
            // good 
        }
    }
}
