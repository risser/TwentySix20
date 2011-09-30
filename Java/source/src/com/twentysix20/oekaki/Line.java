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
* Created on Nov 5, 2008
*/
package com.twentysix20.oekaki;


public class Line {

    final private int[] numbers;
    final private int actualSize;

//    public Line() {}

    public Line(int[] numbers) {
        if ((numbers.length == 1) && (numbers[0] == 0))
            this.numbers = new int[]{};
        else
            this.numbers = numbers;
        this.actualSize = calcActualSize();
    }

    public int size() {
        return numbers.length;
    }

    public int get(int index) {
        return numbers[index];
    }

    public int actualSize() {
        return actualSize;
    }

    private int calcActualSize() {
        int total = 0;
        for (int value : numbers)
            total += value;

        if (numbers.length > 1)
            total += numbers.length - 1;

        return total;
    }

    public int largestValue() {
        int max = 0;
        for (int value : numbers)
            if (value > max)
                max = value;
        return max;
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("[");
        for (int val : numbers)
            buf.append(val).append(",");
        buf.replace(buf.length()-1, buf.length(), "]:");
        buf.append(actualSize);
        return buf.toString();
    }
}