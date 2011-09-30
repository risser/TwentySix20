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
* Created on May 30, 2007
*/
package com.twentysix20.lolcode;

import java.math.BigDecimal;

import com.twentysix20.lolcode.Univar;
import com.twentysix20.lolcode.exception.NoobAccessException;
import com.twentysix20.testutil.TestCase2620;

public class UnivarTests extends TestCase2620 {
    public void testStringInOut() {
        Univar v = new Univar("foo");
        assertEquals("foo", v.strVal());
        assertEquals(true, v.boolVal());
        assertEquals(new BigDecimal("0"), v.numVal());
        assertFalse(v.isNoob());
    }

    public void testNullValues() {
        Univar v = new Univar();
        assertEquals(false, v.boolVal());
        assertTrue(v.isNoob());
        try {
            String s = v.strVal();
            fail("Should have thrown Noob Exception.");
        } catch (NoobAccessException e) {
            // good
        }
        try {
            BigDecimal d = v.numVal();
            fail("Should have thrown Noob Exception.");
        } catch (NoobAccessException e) {
            // good
        }
    }

    public void testBooleanValues() {
        Univar v = new Univar(true);
        assertEquals(Univar.TRUE, v.strVal());
        assertEquals(new BigDecimal("1"), v.numVal());
        assertEquals(true, v.boolVal());
        assertFalse(v.isNoob());

        Univar w = new Univar(false);
        assertEquals(Univar.FALSE, w.strVal());
        assertEquals(new BigDecimal("0"), w.numVal());
        assertEquals(false, w.boolVal());
        assertFalse(v.isNoob());
    }

    public void testStringBooleanValues() {
        Univar v = new Univar(Univar.TRUE);
        assertEquals(Univar.TRUE, v.strVal());
        assertEquals(new BigDecimal("1"), v.numVal());
        assertEquals(true, v.boolVal());
        assertFalse(v.isNoob());

        Univar w = new Univar(Univar.FALSE);
        assertEquals(Univar.FALSE, w.strVal());
        assertEquals(new BigDecimal("0"), w.numVal());
        assertEquals(false, w.boolVal());
        assertFalse(v.isNoob());
    }

    public void testStringIntValues() {
        Univar v = new Univar("5");
        assertEquals("5", v.strVal());
        assertEquals(new BigDecimal("5"), v.numVal());
        assertEquals(true, v.boolVal());
        assertFalse(v.isNoob());

        Univar w = new Univar("0");
        assertEquals("0", w.strVal());
        assertEquals(new BigDecimal("0"), w.numVal());
        assertEquals(false, w.boolVal());
        assertFalse(v.isNoob());
    }

    public void testIntInOut() {
        Univar v = new Univar(new BigDecimal("5"));
        assertEquals("5", v.strVal());
        assertEquals(new BigDecimal("5"), v.numVal());
        assertEquals(true, v.boolVal());
        assertFalse(v.isNoob());

        Univar w = new Univar(new BigDecimal("0"));
        assertEquals("0", w.strVal());
        assertEquals(new BigDecimal("0"), w.numVal());
        assertEquals(false, w.boolVal());
        assertFalse(v.isNoob());
    }

    public void testRealInOut() {
        Univar v = new Univar(new BigDecimal("5.25"));
        assertEquals("5.25", v.strVal());
        assertEquals(new BigDecimal("5.25"), v.numVal());
        assertEquals(true, v.boolVal());
        assertFalse(v.isNoob());

        Univar w = new Univar(new BigDecimal("0.001"));
        assertEquals("0.001", w.strVal());
        assertEquals(new BigDecimal("0.001"), w.numVal());
        assertEquals(true, w.boolVal());
        assertFalse(v.isNoob());

        Univar x = new Univar(new BigDecimal("0.00"));
        assertEquals("0.00", x.strVal());
        assertEquals(new BigDecimal("0.00"), x.numVal());
        assertFalse(new BigDecimal("0").equals(x.numVal()));
        assertEquals(false, x.boolVal());
        assertFalse(v.isNoob());
    }
}
