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

import java.util.Arrays;

public class Tray {
    final private CellValue[] values;

    public Tray(int size) {
        if (size < 1) throw new IndexOutOfBoundsException("Size must be one or greater.");
        values = new CellValue[size];
        Arrays.fill(values, CellValue.UNKNOWN);
//        for (int i = 0; i < values.length; i++)
//            values[i] = CellValue.UNKNOWN;
    }

    public Tray(CellValue[] startingValues) {
        values = startingValues.clone();
    }

    public int size() {
        return values.length;
    }

    public void set(int i, CellValue value) {
//        if (value == CellValue.UNKNOWN) throw new IllegalArgumentException("Can't set to UNKNOWN.  Use 'clear'.");
        values[i] = value;
    }

    public CellValue at(int i) {
        return values[i];
    }

    public void clear(int i) {
        values[i] = CellValue.UNKNOWN;
    }

    public boolean matches(Tray tray2) {
        if (this.size() != tray2.size()) return false;

        for (int i = 0; i < tray2.size(); i++) {
            switch (tray2.at(i)) {
            case FILLED:
                if (this.at(i) == CellValue.EMPTY)
                    return false;
                break;
            case EMPTY:
                if (this.at(i) == CellValue.FILLED)
                    return false;
                break;
            case UNKNOWN:
            case INDETERMINATE:
            }
        }

        return true;
    }

    public boolean isDefinitive() {
        for (CellValue value : values)
            if ((value == CellValue.UNKNOWN) || (value == CellValue.INDETERMINATE))
                return false;
        return true;
    }

    public void merge(Tray tray) {
        if (tray.size() != this.size())
            throw new IndexOutOfBoundsException("Trays are different sizes.");
        for (int i = 0; i < tray.size(); i++) {
            if (tray.at(i) == CellValue.UNKNOWN) continue;
        
            switch (this.at(i)) {
            case UNKNOWN:
                this.set(i, tray.at(i));
                break;
            case FILLED:
            case EMPTY:
                if (tray.at(i) != this.at(i))
                    this.set(i, CellValue.INDETERMINATE);
            case INDETERMINATE:
            }
        }
    }

    public String toString() {
        StringBuffer buf = new StringBuffer("Tray["+size()+":");
        for (CellValue value : values)
            buf.append(value).append(",");
        return buf.substring(0, buf.length()-1)+"]";
    }

    public String prettyPrint() {
        StringBuffer buf = new StringBuffer(":");
        for (CellValue value : values)
            switch (value) {
            case FILLED: 
                buf.append("X"); 
                break;
            case EMPTY:
                buf.append(" "); 
                break;
            case INDETERMINATE:
            case UNKNOWN:
                buf.append("·"); 
            }
            buf.append(":");
        return buf.toString();
    }
}
