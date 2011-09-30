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

public class TraySet_LineConstructor_TestCase extends TestCase2620 {
    public void testOneInOne() {
        Line line = new Line(new int[]{1});
        TraySet set = new TraySetCreator(line, 1).traySet();
        assertEquals(1, set.size());
        Tray tray = set.collate(new Tray(1));
        assertEquals(CellValue.FILLED, tray.at(0));
    }

    public void testOneInTwo() {
        Line line = new Line(new int[]{1});
        TraySet set = new TraySetCreator(line, 2).traySet();
        assertEquals(2, set.size());
        Tray tray = set.collate(new Tray(2));
        assertEquals(CellValue.INDETERMINATE, tray.at(0));
        assertEquals(CellValue.INDETERMINATE, tray.at(1));
    }

    public void testOneAndOneInThree() {
        Line line = new Line(new int[]{1,1});
        TraySet set = new TraySetCreator(line, 3).traySet();
        assertEquals(1, set.size());
        Tray tray = set.collate(new Tray(3));
        assertEquals(CellValue.FILLED, tray.at(0));
        assertEquals(CellValue.EMPTY, tray.at(1));
        assertEquals(CellValue.FILLED, tray.at(2));
    }

    public void testOneAndOneInFour() {
        Line line = new Line(new int[]{1,1});
        TraySet set = new TraySetCreator(line, 4).traySet();
        assertEquals(3, set.size());
        Tray tray = set.collate(new Tray(4));
        assertEquals(CellValue.INDETERMINATE, tray.at(0));
        assertEquals(CellValue.INDETERMINATE, tray.at(1));
        assertEquals(CellValue.INDETERMINATE, tray.at(2));
        assertEquals(CellValue.INDETERMINATE, tray.at(3));
    }

    public void testOneThruFiveIn19() {
        Line line = new Line(new int[]{1,2,3,4,5});
        TraySet set = new TraySetCreator(line, 19).traySet();
        assertEquals(1, set.size());
    }

    public void testOneThruFiveIn20() {
        Line line = new Line(new int[]{1,2,3,4,5});
        TraySet set = new TraySetCreator(line, 20).traySet();
        assertEquals(6, set.size());
    }

    public void testOneIn20() {
        Line line = new Line(new int[]{1});
        TraySet set = new TraySetCreator(line, 20).traySet();
        assertEquals(20, set.size());
    }

    public void testTenIn20() {
        Line line = new Line(new int[]{10});
        TraySet set = new TraySetCreator(line, 20).traySet();
        assertEquals(11, set.size());
    }

    public void testZeroIn20() {
        Line line = new Line(new int[]{});
        TraySet set = new TraySetCreator(line, 20).traySet();
        assertEquals(1, set.size());
    }

    public void test722In20() {
        Line line = new Line(new int[]{7,2,2});
        TraySet set = new TraySetCreator(line, 20).traySet();
        assertEquals(120, set.size());
    }

    public void test212231In20() {
        Line line = new Line(new int[]{2,1,2,2,3,1});
        TraySet set = new TraySetCreator(line, 20).traySet();
        assertEquals(210, set.size());
    }

    public void testTenOnesIn30() {
        // 3.5 secs
        Line line = new Line(new int[]{1,1,1,1,1,1,1,1,1,1});
        TraySet set = new TraySetCreator(line, 30).traySet();
        assertEquals(352716, set.size());
    }

    public void testTenOnesIn50() {
        // BREAKS IT!
//        Line line = new Line(new int[]{1,1,1,1,1,1,1,1,1,1});
//        TraySet set = new TraySetCreator(line, 50).traySet();
//        assertEquals(352716, set.size());
    }
}