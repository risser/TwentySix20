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
* Created on Dec 5, 2008
*/
package com.twentysix20.oekaki;

public class CellPositionAndValue {
    final private int position;
    final private CellValue value;

    public CellPositionAndValue(int position, CellValue value) {
        this.position = position;
        this.value = value;
    }

    public int getPosition() {
        return position;
    }
    public CellValue getValue() {
        return value;
    }
}