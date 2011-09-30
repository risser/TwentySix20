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

public class TrayMergeTestCase extends TestCase2620 {

    public void testMergeFilled() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN});
        Tray tray2 = new Tray(new CellValue[]{CellValue.FILLED});
        base.merge(tray2);
        assertEquals(CellValue.FILLED, base.at(0));
    }

    public void testMergeEmpty() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY});
        base.merge(tray2);
        assertEquals(CellValue.EMPTY, base.at(0));
    }

    public void testMergeInd() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN});
        Tray tray2 = new Tray(new CellValue[]{CellValue.INDETERMINATE});
        base.merge(tray2);
        assertEquals(CellValue.INDETERMINATE, base.at(0));
    }

    public void testMisMatchedSizes() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN});
        Tray tray2 = new Tray(new CellValue[]{CellValue.FILLED, CellValue.FILLED});
        try {
            base.merge(tray2);
            fail("Should have thrown IndexOutOfBoundsException.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }
    }

    public void testMixedFilled() {
        Tray base = new Tray(new CellValue[]{CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY});
        base.merge(tray2);
        assertEquals(CellValue.INDETERMINATE, base.at(0));
    }

    public void testMixedEmpty() {
        Tray base = new Tray(new CellValue[]{CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.FILLED});
        base.merge(tray2);
        assertEquals(CellValue.INDETERMINATE, base.at(0));
    }

    public void testMixedThree() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY});
        base.merge(tray1);
        base.merge(tray2);
        assertEquals(CellValue.INDETERMINATE, base.at(0));
    }

    public void testMergeTwo() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.UNKNOWN});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        base.merge(tray2);
        assertEquals(CellValue.EMPTY, base.at(0));
        assertEquals(CellValue.FILLED, base.at(1));
    }

    public void testMergeThree() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.UNKNOWN});
        Tray tray1 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY});
        base.merge(tray2);
        base.merge(tray1);
        assertEquals(CellValue.INDETERMINATE, base.at(0));
        assertEquals(CellValue.INDETERMINATE, base.at(1));
    }

    public void testMergingTwoAgainstFive() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray3 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY});
        Tray tray4 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED});
        base.merge(tray1);
        base.merge(tray2);
        base.merge(tray3);
        base.merge(tray4);
        assertEquals(CellValue.INDETERMINATE, base.at(0));
        assertEquals(CellValue.INDETERMINATE, base.at(1));
        assertEquals(CellValue.INDETERMINATE, base.at(2));
        assertEquals(CellValue.INDETERMINATE, base.at(3));
        assertEquals(CellValue.INDETERMINATE, base.at(4));
    }

    public void testMergingTwoAgainstFiveWithEmpties() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray3 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY});
        base.merge(tray2);
        base.merge(tray3);
        assertEquals(CellValue.EMPTY, base.at(0));
        assertEquals(CellValue.INDETERMINATE, base.at(1));
        assertEquals(CellValue.FILLED, base.at(2));
        assertEquals(CellValue.INDETERMINATE, base.at(3));
        assertEquals(CellValue.EMPTY, base.at(4));
    }

    public void testMergingThreeAgainstFive() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY});
        Tray tray3 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.FILLED});
        base.merge(tray1);
        base.merge(tray2);
        base.merge(tray3);
        assertEquals(CellValue.INDETERMINATE, base.at(0));
        assertEquals(CellValue.INDETERMINATE, base.at(1));
        assertEquals(CellValue.FILLED, base.at(2));
        assertEquals(CellValue.INDETERMINATE, base.at(3));
        assertEquals(CellValue.INDETERMINATE, base.at(4));
    }

    public void testMergingFourAgainstFive() {
        Tray base = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN,CellValue.UNKNOWN});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.FILLED,CellValue.FILLED});
        base.merge(tray1);
        base.merge(tray2);
        assertEquals(CellValue.INDETERMINATE, base.at(0));
        assertEquals(CellValue.FILLED, base.at(1));
        assertEquals(CellValue.FILLED, base.at(2));
        assertEquals(CellValue.FILLED, base.at(3));
        assertEquals(CellValue.INDETERMINATE, base.at(4));
    }
    /*

    public void testMatchTwoEmptiesAndOneAndOneOther() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        assertNoMatch(tray1, tray2);
    }

    public void testMatchTwoFilledAndOneAndOneOther() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        assertNoMatch(tray1, tray2);
    }

    public void testMatchOneAndOnes() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        assertMatch(tray1, tray2);
    }

    public void testTwoAgainstUnknowns() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.UNKNOWN});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        Tray tray3 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY});
        Tray tray4 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED});
        Tray tray5 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY});
        Tray tray6 = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.INDETERMINATE});
        Tray tray7 = new Tray(new CellValue[]{CellValue.INDETERMINATE,CellValue.UNKNOWN});

        assertMatch(tray1, tray2);
        assertMatch(tray1, tray3);
        assertMatch(tray1, tray4);
        assertMatch(tray1, tray5);
        assertMatch(tray1, tray6);
        assertMatch(tray1, tray7);
    }

    public void testTwoAgainstIndeterminate() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.INDETERMINATE,CellValue.INDETERMINATE});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        Tray tray3 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY});
        Tray tray4 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED});
        Tray tray5 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.EMPTY});
        Tray tray6 = new Tray(new CellValue[]{CellValue.UNKNOWN,CellValue.INDETERMINATE});
        Tray tray7 = new Tray(new CellValue[]{CellValue.INDETERMINATE,CellValue.UNKNOWN});

        assertMatch(tray1, tray2);
        assertTrue(tray1.matches(tray5));
        assertTrue(tray3.matches(tray1));
        assertTrue(tray1.matches(tray5));
        assertTrue(tray4.matches(tray1));
        assertMatch(tray1, tray5);
        assertMatch(tray1, tray6);
        assertMatch(tray1, tray7);
    }

    public void testFloatingTwoAgainstFilledOne() {
        Tray base = new Tray(new CellValue[]{CellValue.INDETERMINATE,CellValue.INDETERMINATE,CellValue.FILLED,CellValue.INDETERMINATE,CellValue.INDETERMINATE});
        Tray empty = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray3 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY});
        Tray tray4 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED});

        assertNoMatch(base, empty);
        assertNoMatch(base, tray1);
        assertMatch(base, tray2);
        assertMatch(base, tray3);
        assertNoMatch(base, tray4);
    }

    private void assertNoMatch(Tray tray1, Tray tray2) {
        assertFalse(tray1.matches(tray2));
        assertFalse(tray2.matches(tray1));
    }

    private void assertMatch(Tray tray1, Tray tray2) {
        assertTrue(tray1.matches(tray2));
        assertTrue(tray2.matches(tray1));
    }
    */
}