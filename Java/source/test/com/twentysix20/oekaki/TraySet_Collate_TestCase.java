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

public class TraySet_Collate_TestCase extends TestCase2620 {
    public void testTwoAgainstUnknowns() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        TraySet set = new TraySet();
        set.add(tray1);
        set.add(tray2);
        Tray collated = set.collate(new Tray(2));
        assertEquals(2, set.size());
        assertEquals(CellValue.INDETERMINATE, collated.at(0));
        assertEquals(CellValue.INDETERMINATE, collated.at(1));
        assertFalse(set.isSolved());
    }

    public void testTwoAgainstOtherKnown() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.EMPTY});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        TraySet set = new TraySet();
        set.add(tray1);
        set.add(tray2);
        Tray collated = set.collate(base);
        assertEquals(1, set.size());
        assertEquals(CellValue.FILLED, collated.at(0));
        assertEquals(CellValue.EMPTY, collated.at(1));
        assertTrue(set.isSolved());
    }

    public void testTwoAgainstOneKnown() {
        Tray base = new Tray(new CellValue[]{CellValue.FILLED,CellValue.UNKNOWN});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        TraySet set = new TraySet();
        set.add(tray1);
        set.add(tray2);
        Tray collated = set.collate(base);
        assertEquals(1, set.size());
        assertEquals(CellValue.FILLED, collated.at(0));
        assertEquals(CellValue.EMPTY, collated.at(1));
        assertTrue(set.isSolved());
    }

    public void testTwoAgainstInvalid() {
        Tray base = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        TraySet set = new TraySet();
        set.add(tray1);
        set.add(tray2);
        try {
            Tray collated = set.collate(base);
            fail("Should have thrown exception.");
        } catch(IllegalStateException e) {
            // yay
        }
        assertEquals(0, set.size());
        assertFalse(set.isSolved());
    }

    public void testSixTier() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN});
//    1,2 
//    +-++--
//    +--++-
//    +---++
//    -+-++-
//    -+--++
//    --+-++
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY});
        Tray tray3 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED});
        Tray tray4 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY});
        Tray tray5 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED});
        Tray tray6 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED});
        TraySet set = new TraySet();
        set.add(tray1);
        set.add(tray2);
        set.add(tray3);
        set.add(tray4);
        set.add(tray5);
        set.add(tray6);

        Tray collated = set.collate(base);
        assertEquals(6, set.size());
        assertEquals(CellValue.INDETERMINATE, collated.at(0));
        assertEquals(CellValue.INDETERMINATE, collated.at(1));
        assertEquals(CellValue.INDETERMINATE, collated.at(2));
        assertEquals(CellValue.INDETERMINATE, collated.at(3));
        assertEquals(CellValue.INDETERMINATE, collated.at(4));
        assertEquals(CellValue.INDETERMINATE, collated.at(5));
        assertFalse(set.isSolved());

        base.set(5, CellValue.FILLED);
        collated = set.collate(base);
        assertEquals(3, set.size());
        assertEquals(CellValue.INDETERMINATE, collated.at(0));
        assertEquals(CellValue.INDETERMINATE, collated.at(1));
        assertEquals(CellValue.INDETERMINATE, collated.at(2));
        assertEquals(CellValue.EMPTY, collated.at(3));
        assertEquals(CellValue.FILLED, collated.at(4));
        assertEquals(CellValue.FILLED, collated.at(5));
        assertFalse(set.isSolved());

        base.set(4, CellValue.FILLED);
        base.set(3, CellValue.EMPTY);
        base.set(1, CellValue.EMPTY);
        collated = set.collate(base);
        assertEquals(2, set.size());
        assertEquals(CellValue.INDETERMINATE, collated.at(0));
        assertEquals(CellValue.EMPTY, collated.at(1));
        assertEquals(CellValue.INDETERMINATE, collated.at(2));
        assertEquals(CellValue.EMPTY, collated.at(3));
        assertEquals(CellValue.FILLED, collated.at(4));
        assertEquals(CellValue.FILLED, collated.at(5));
        assertFalse(set.isSolved());

        base.set(0, CellValue.EMPTY);
        collated = set.collate(base);
        assertEquals(1, set.size());
        assertEquals(CellValue.EMPTY, collated.at(0));
        assertEquals(CellValue.EMPTY, collated.at(1));
        assertEquals(CellValue.FILLED, collated.at(2));
        assertEquals(CellValue.EMPTY, collated.at(3));
        assertEquals(CellValue.FILLED, collated.at(4));
        assertEquals(CellValue.FILLED, collated.at(5));
        assertTrue(set.isSolved());
    }
}