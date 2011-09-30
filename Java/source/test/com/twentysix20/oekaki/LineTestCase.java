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

public class LineTestCase extends TestCase2620 {
    public void testCreateWithArray() {
        Line line = new Line(new int[]{1,3,2,5});
        assertEquals(4, line.size());
        assertEquals(1, line.get(0));
        assertEquals(3, line.get(1));
        assertEquals(2, line.get(2));
        assertEquals(5, line.get(3));
        try {
            line.get(4);
            fail ("Should have thrown index out of bounds exception.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }
    }

    public void testSingleItem() {
        Line line = new Line(new int[]{5});
        assertEquals(1, line.size());
        assertEquals(5, line.get(0));
        try {
            line.get(1);
            fail ("Should have thrown index out of bounds exception.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }
    }

    public void testEmptyLine() {
        Line line = new Line(new int[]{});
        assertEquals(0, line.size());
        try {
            line.get(0);
            fail ("Should have thrown index out of bounds exception.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }
    }

    public void testZeroLine() {
        Line line = new Line(new int[]{0});
        assertEquals(0, line.size());
        try {
            line.get(0);
            fail ("Should have thrown index out of bounds exception.");
        } catch (IndexOutOfBoundsException e) {
            // yay
        }
    }

    public void testTotalSize() {
        assertEquals(1, new Line(new int[]{1}).actualSize());
        assertEquals(2, new Line(new int[]{2}).actualSize());
        assertEquals(3, new Line(new int[]{1,1}).actualSize());
        assertEquals(9, new Line(new int[]{3,5}).actualSize());
        assertEquals(19, new Line(new int[]{1,2,3,4,5}).actualSize());
    }
}