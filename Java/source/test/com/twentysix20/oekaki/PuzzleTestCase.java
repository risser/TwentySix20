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

public class PuzzleTestCase extends TestCase2620 {

    public void test3By3() {
        Line line2 = new Line(new int[]{2});
        Line line1_1 = new Line(new int[]{1,1});
        Line line1 = new Line(new int[]{1});

        Board board = new Board(3,3);
        board.addRow(line1_1);
        board.addRow(line1);
        board.addRow(line1);
        board.addColumn(line1);
        board.addColumn(line2);
        board.addColumn(line1);

        board.process();
    }

    public void testHeart() {
        Board board = new Board(14,15);
        board.addRow(new Line(new int[]{4,4}));
        board.addRow(new Line(new int[]{6,6}));
        board.addRow(new Line(new int[]{15}));
        board.addRow(new Line(new int[]{15}));
        board.addRow(new Line(new int[]{15}));
        board.addRow(new Line(new int[]{15}));
        board.addRow(new Line(new int[]{13}));
        board.addRow(new Line(new int[]{13}));
        board.addRow(new Line(new int[]{11}));
        board.addRow(new Line(new int[]{9}));
        board.addRow(new Line(new int[]{7}));
        board.addRow(new Line(new int[]{5}));
        board.addRow(new Line(new int[]{3}));
        board.addRow(new Line(new int[]{1}));
        board.addColumn(new Line(new int[]{4}));
        board.addColumn(new Line(new int[]{7}));
        board.addColumn(new Line(new int[]{9}));
        board.addColumn(new Line(new int[]{10}));
        board.addColumn(new Line(new int[]{11}));
        board.addColumn(new Line(new int[]{12}));
        board.addColumn(new Line(new int[]{12}));
        board.addColumn(new Line(new int[]{12}));
        board.addColumn(new Line(new int[]{12}));
        board.addColumn(new Line(new int[]{12}));
        board.addColumn(new Line(new int[]{11}));
        board.addColumn(new Line(new int[]{10}));
        board.addColumn(new Line(new int[]{9}));
        board.addColumn(new Line(new int[]{7}));
        board.addColumn(new Line(new int[]{4}));

        board.process();
    }

    public void testOtter() {
        Board board = new Board(20,20);
        board.addRow(new Line(new int[]{6}));
        board.addRow(new Line(new int[]{2,2}));
        board.addRow(new Line(new int[]{2,1,1,1}));
        board.addRow(new Line(new int[]{1,1,1,2}));
        board.addRow(new Line(new int[]{1,1}));
        board.addRow(new Line(new int[]{4,3,5}));
        board.addRow(new Line(new int[]{1,1,3,1}));
        board.addRow(new Line(new int[]{4,5,1}));
        board.addRow(new Line(new int[]{2,9}));
        board.addRow(new Line(new int[]{2,2,4,2}));
        board.addRow(new Line(new int[]{3,1,1}));
        board.addRow(new Line(new int[]{1,2,2,5}));
        board.addRow(new Line(new int[]{1,1,2,1,1}));
        board.addRow(new Line(new int[]{1,1,1,2,1}));
        board.addRow(new Line(new int[]{1,3,4}));
        board.addRow(new Line(new int[]{2,3,2}));
        board.addRow(new Line(new int[]{3,5,1}));
        board.addRow(new Line(new int[]{1,2,2,2,1}));
        board.addRow(new Line(new int[]{2,1,4,1}));
        board.addRow(new Line(new int[]{4,6,5}));
        board.addColumn(new Line(new int[]{1,1,3,2}));
        board.addColumn(new Line(new int[]{7,2,2}));
        board.addColumn(new Line(new int[]{2,1,3,2,1}));
        board.addColumn(new Line(new int[]{2,1,1,3,1,1}));
        board.addColumn(new Line(new int[]{1,2,5,2,1}));
        board.addColumn(new Line(new int[]{1,2,1,1}));
        board.addColumn(new Line(new int[]{1,1,2,2}));
        board.addColumn(new Line(new int[]{1,2,2,1,1,3}));
        board.addColumn(new Line(new int[]{2,1,2,2,3,1}));
        board.addColumn(new Line(new int[]{3,4,2,1,1}));
        board.addColumn(new Line(new int[]{9,1,1}));
        board.addColumn(new Line(new int[]{5,1,1}));
        board.addColumn(new Line(new int[]{1,3,1,2}));
        board.addColumn(new Line(new int[]{1,1,2}));
        board.addColumn(new Line(new int[]{5,2}));
        board.addColumn(new Line(new int[]{3,2}));
        board.addColumn(new Line(new int[]{4,1}));
        board.addColumn(new Line(new int[]{1,2,1}));
        board.addColumn(new Line(new int[]{1,2,1}));
        board.addColumn(new Line(new int[]{9}));

        board.process();
    }
/*
    public void testClown() {
        Board board = new Board(35,35);
        board.addRow(new Line(new int[]{4,3}));
        board.addRow(new Line(new int[]{6,5}));
        board.addRow(new Line(new int[]{10,2,4}));
        board.addRow(new Line(new int[]{10,7}));
        board.addRow(new Line(new int[]{2,1,1,3,7}));
        board.addRow(new Line(new int[]{2,10,3,5}));
        board.addRow(new Line(new int[]{3,1,3,1,1,2,2,3,3}));
        board.addRow(new Line(new int[]{1,1,13,1,1,1}));
        board.addRow(new Line(new int[]{1,1,13,1,3}));
        board.addRow(new Line(new int[]{4,3,1,1,1,1,1,1,3,3}));
        board.addRow(new Line(new int[]{3,1,1,1,1,1,1,1,4,2})); //*
        board.addRow(new Line(new int[]{1,1,5,1,1,1,1,4,1,1}));
        board.addRow(new Line(new int[]{1,2,1,8,3,1,1,1}));
        board.addRow(new Line(new int[]{3,2,2,3}));
        board.addRow(new Line(new int[]{2,2,2,2,3,2}));//*
        board.addRow(new Line(new int[]{3,1,5,5,1,3}));
        board.addRow(new Line(new int[]{2,5,1,1,1,1,1,1,5,2}));
        board.addRow(new Line(new int[]{1,3,1,5,5,1,3,1}));
        board.addRow(new Line(new int[]{1,5,1,1,1,1,1,1,5,1}));
        board.addRow(new Line(new int[]{1,1,1,5,5,1,1,2}));
        board.addRow(new Line(new int[]{2,1,2,1,2}));//*
        board.addRow(new Line(new int[]{3,3,4,2,3}));
        board.addRow(new Line(new int[]{1,1,4,1,1}));//*
        board.addRow(new Line(new int[]{2,2,2,2,2}));
        board.addRow(new Line(new int[]{2,2,3,3,2,2}));
        board.addRow(new Line(new int[]{1,1,1,5,5,1,1,1})); //*
        board.addRow(new Line(new int[]{1,2,1,2,6,2,1,1,1}));//*
        board.addRow(new Line(new int[]{2,2,3,4,3,2,2}));
        board.addRow(new Line(new int[]{2,2,1,3,3,1,2,2})); //*
        board.addRow(new Line(new int[]{4,2,8,2,4}));
        board.addRow(new Line(new int[]{4,5}));
        board.addRow(new Line(new int[]{4,3,8}));
        board.addRow(new Line(new int[]{4,13,5}));
        board.addRow(new Line(new int[]{10,10}));
        board.addRow(new Line(new int[]{8,8}));

        board.addColumn(new Line(new int[]{3}));
        board.addColumn(new Line(new int[]{4,2,2}));
        board.addColumn(new Line(new int[]{1,1,1,2,4}));
        board.addColumn(new Line(new int[]{2,3,1,1,2,2}));
        board.addColumn(new Line(new int[]{1,1,3,3,2}));
        board.addColumn(new Line(new int[]{1,2,4,2,1,2}));
        board.addColumn(new Line(new int[]{4,1,2,1,3,1,1,4}));
        board.addColumn(new Line(new int[]{1,1,1,1,1,2,2,4}));
        board.addColumn(new Line(new int[]{7,2,6,5,5}));
        board.addColumn(new Line(new int[]{1,1,3,1,3,2}));
        board.addColumn(new Line(new int[]{6,3,3}));
        board.addColumn(new Line(new int[]{2,2,5,3,5}));
        board.addColumn(new Line(new int[]{11,1,1,1,5,4}));
        board.addColumn(new Line(new int[]{7,1,6,2,3,4}));
        board.addColumn(new Line(new int[]{3,8,2,1,1,3,2,2}));
        board.addColumn(new Line(new int[]{6,2,1,5,2,3,1,1}));
        board.addColumn(new Line(new int[]{4,8,4,2,1,1}));
        board.addColumn(new Line(new int[]{6,2,1,4,2,1,1}));
        board.addColumn(new Line(new int[]{4,8,5,2,3,1,1}));
        board.addColumn(new Line(new int[]{5,2,2,1,1,3,2,2}));
        board.addColumn(new Line(new int[]{11,6,2,3,4}));
        board.addColumn(new Line(new int[]{7,1,1,1,1,5,4}));
        board.addColumn(new Line(new int[]{6,5,3,5}));
        board.addColumn(new Line(new int[]{1,2,4,2}));
        board.addColumn(new Line(new int[]{9,1,6}));
        board.addColumn(new Line(new int[]{1,3,2,5,5,5}));
        board.addColumn(new Line(new int[]{5,1,1,1,1,2,7}));
        board.addColumn(new Line(new int[]{1,1,3,1,3,1,4}));
        board.addColumn(new Line(new int[]{3,4,2,4,2,1,2}));
        board.addColumn(new Line(new int[]{5,1,1,3,3,2}));
        board.addColumn(new Line(new int[]{2,4,2,3,1,1,2,2}));
        board.addColumn(new Line(new int[]{7,1,1,1,2,4}));
        board.addColumn(new Line(new int[]{7,3,2,2}));
        board.addColumn(new Line(new int[]{5,4}));
        board.addColumn(new Line(new int[]{3}));

        board.process();
    }
    */
}