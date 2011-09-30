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

public class TrayMatchTestCase extends TestCase2620 {

    public void testMatchFilledAndFilled() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.FILLED});
        assertMatch(tray1, tray2);
    }

    public void testMisMatchedSizes() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.FILLED, CellValue.FILLED});
        assertNoMatch(tray1, tray2);
    }

    public void testNoMatchFilledAndEmpty() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY});
        assertNoMatch(tray1, tray2);
    }

    public void testMatchFilledAndUnknown() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.UNKNOWN});
        assertMatch(tray1, tray2);
    }

    public void testMatchEmptyAndUnknown() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.UNKNOWN});
        assertMatch(tray1, tray2);
    }

    public void testMatchFilledAndIndeterminate() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED});
        Tray tray2 = new Tray(new CellValue[]{CellValue.INDETERMINATE});
        assertMatch(tray1, tray2);
    }

    public void testMatchEmptyAndIndeterminate() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.INDETERMINATE});
        assertMatch(tray1, tray2);
    }

    public void testMatchTwoEmptiesAndOneAndOne() {
        Tray tray1 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED});
        assertNoMatch(tray1, tray2);
    }

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

    public void testFloatingTwoAgainstEmptyOne() {
        Tray base = new Tray(new CellValue[]{CellValue.INDETERMINATE,CellValue.INDETERMINATE,CellValue.EMPTY,CellValue.INDETERMINATE,CellValue.INDETERMINATE});
        Tray empty = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray1 = new Tray(new CellValue[]{CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray2 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY,CellValue.EMPTY});
        Tray tray3 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED,CellValue.EMPTY});
        Tray tray4 = new Tray(new CellValue[]{CellValue.EMPTY,CellValue.EMPTY,CellValue.EMPTY,CellValue.FILLED,CellValue.FILLED});

        assertMatch(base, empty);
        assertMatch(base, tray1);
        assertNoMatch(base, tray2);
        assertNoMatch(base, tray3);
        assertMatch(base, tray4);
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
}