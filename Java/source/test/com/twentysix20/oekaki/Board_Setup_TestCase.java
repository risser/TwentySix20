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

public class Board_Setup_TestCase extends TestCase2620 {

    public void testNew3By3() {
        Line line2 = new Line(new int[]{1,1});
        Line line1 = new Line(new int[]{1});

        Board board = new Board(3,3);
        assertEquals(0, board.addRow(line2));
        assertEquals(1, board.addRow(line1));
        assertEquals(2, board.addRow(line2));

        assertEquals(0, board.addColumn(line2));
        assertEquals(1, board.addColumn(line1));
        assertEquals(2, board.addColumn(line2));
    }

    public void testTooMany() {
        Line line2 = new Line(new int[]{1,1});
        Line line1 = new Line(new int[]{1});

        Board board = new Board(3,3);
        board.addRow(line2);
        board.addRow(line1);
        board.addRow(line2);
        try {
            board.addRow(line2);
            fail("Should have thrown index out of bounds.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }

        board.addColumn(line2);
        board.addColumn(line1);
        board.addColumn(line2);
        try {
            board.addColumn(line2);
            fail("Should have thrown index out of bounds.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }
    }

    public void testLineTooBig() {
        Line line1 = new Line(new int[]{4});

        Board board = new Board(3,3);
        try {
            board.addRow(line1);
            fail("Should have thrown index out of bounds.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }

        try {
            board.addColumn(line1);
            fail("Should have thrown index out of bounds.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }
    }

    public void testNotEnoughColumnsForRowLine() {
        Line line1 = new Line(new int[]{7});

        Board board = new Board(10,5);
        try {
            board.addRow(line1);
            fail("Should have thrown index out of bounds.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }

        board.addColumn(line1);
    }

    public void testNotEnoughRowsForColumnLine() {
        Line line1 = new Line(new int[]{7});

        Board board = new Board(5,10);
        try {
            board.addColumn(line1);
            fail("Should have thrown index out of bounds.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }

        board.addRow(line1);
    }
}