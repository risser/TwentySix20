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
* Copyright (c) 2008 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Oct 29, 2008
*/
package com.twentysix20.oekaki;

import com.twentysix20.testutil.TestCase2620;

public class TrayTestCase extends TestCase2620 {

    public void testErrorWithZeroTray() {
        try {
            Tray tray = new Tray(0);
            fail("Should have thrown IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException e) {
            // yay!
        }
    }

    public void testTrayOf1() {
        Tray tray = new Tray(1);
        assertEquals(1, tray.size());
    }

    public void testTrayOf10() {
        Tray tray = new Tray(10);
        assertEquals(10, tray.size());

        assertEquals(CellValue.UNKNOWN, tray.at(0));
        assertEquals(CellValue.UNKNOWN, tray.at(1));
        assertEquals(CellValue.UNKNOWN, tray.at(2));
        assertEquals(CellValue.UNKNOWN, tray.at(3));
        assertEquals(CellValue.UNKNOWN, tray.at(4));
        assertEquals(CellValue.UNKNOWN, tray.at(5));
        assertEquals(CellValue.UNKNOWN, tray.at(6));
        assertEquals(CellValue.UNKNOWN, tray.at(7));
        assertEquals(CellValue.UNKNOWN, tray.at(8));
        assertEquals(CellValue.UNKNOWN, tray.at(9));
    }

    public void testTrayWithInitialValues() {
        CellValue[] start = new CellValue[]{CellValue.UNKNOWN, CellValue.UNKNOWN, CellValue.UNKNOWN, CellValue.FILLED, CellValue.UNKNOWN, CellValue.UNKNOWN, CellValue.UNKNOWN, CellValue.EMPTY, CellValue.INDETERMINATE, CellValue.UNKNOWN};
        Tray tray = new Tray(start);
        assertEquals(10, tray.size());

        assertEquals(CellValue.UNKNOWN, tray.at(0));
        assertEquals(CellValue.UNKNOWN, tray.at(1));
        assertEquals(CellValue.UNKNOWN, tray.at(2));
        assertEquals(CellValue.FILLED, tray.at(3));
        assertEquals(CellValue.UNKNOWN, tray.at(4));
        assertEquals(CellValue.UNKNOWN, tray.at(5));
        assertEquals(CellValue.UNKNOWN, tray.at(6));
        assertEquals(CellValue.EMPTY, tray.at(7));
        assertEquals(CellValue.INDETERMINATE, tray.at(8));
        assertEquals(CellValue.UNKNOWN, tray.at(9));

        // make sure changes to the original array don't affect the new array
        start[5] = CellValue.FILLED;
        assertEquals(CellValue.UNKNOWN, tray.at(5));
    }

    public void testSetAndGetValue() {
        Tray tray = new Tray(10);
        tray.set(3, CellValue.FILLED);
        tray.set(7, CellValue.EMPTY);
        tray.set(8, CellValue.INDETERMINATE);

        assertEquals(CellValue.UNKNOWN, tray.at(0));
        assertEquals(CellValue.UNKNOWN, tray.at(1));
        assertEquals(CellValue.UNKNOWN, tray.at(2));
        assertEquals(CellValue.FILLED, tray.at(3));
        assertEquals(CellValue.UNKNOWN, tray.at(4));
        assertEquals(CellValue.UNKNOWN, tray.at(5));
        assertEquals(CellValue.UNKNOWN, tray.at(6));
        assertEquals(CellValue.EMPTY, tray.at(7));
        assertEquals(CellValue.INDETERMINATE, tray.at(8));
        assertEquals(CellValue.UNKNOWN, tray.at(9));
    }

    public void errorNotUsingClear() {
        Tray tray = new Tray(10);
        try {
            tray.set(3, CellValue.UNKNOWN);
            fail("Should have thrown IllegalArgumentException.");
        } catch (IllegalArgumentException e) {
            // yay
        }
    }

    public void testClear() {
        Tray tray = new Tray(10);
        tray.set(3, CellValue.FILLED);
        tray.set(7, CellValue.EMPTY);
        tray.set(8, CellValue.INDETERMINATE);

        assertEquals(CellValue.FILLED, tray.at(3));
        assertEquals(CellValue.EMPTY, tray.at(7));
        assertEquals(CellValue.INDETERMINATE, tray.at(8));

        tray.clear(8);
        tray.clear(7);
        tray.clear(3);

        assertEquals(CellValue.UNKNOWN, tray.at(0));
        assertEquals(CellValue.UNKNOWN, tray.at(1));
        assertEquals(CellValue.UNKNOWN, tray.at(2));
        assertEquals(CellValue.UNKNOWN, tray.at(3));
        assertEquals(CellValue.UNKNOWN, tray.at(4));
        assertEquals(CellValue.UNKNOWN, tray.at(5));
        assertEquals(CellValue.UNKNOWN, tray.at(6));
        assertEquals(CellValue.UNKNOWN, tray.at(7));
        assertEquals(CellValue.UNKNOWN, tray.at(8));
        assertEquals(CellValue.UNKNOWN, tray.at(9));
    }

    public void testDefinitive() {
        Tray tray = new Tray(3);
        assertFalse(tray.isDefinitive());

        tray.set(2, CellValue.FILLED);
        assertFalse(tray.isDefinitive());

        tray.set(1, CellValue.FILLED);
        assertFalse(tray.isDefinitive());

        tray.set(0, CellValue.FILLED);
        assertTrue(tray.isDefinitive());

        tray.set(0, CellValue.EMPTY);
        assertTrue(tray.isDefinitive());

        tray.set(1, CellValue.INDETERMINATE);
        assertFalse(tray.isDefinitive());
    }
}