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
* Copyright (c) 2007 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on May 30, 2007
*/
package com.twentysix20.lolcode;

import java.math.BigDecimal;

import com.twentysix20.lolcode.exception.NoobAccessException;

public class Univar {
    private static final BigDecimal ZERO = new BigDecimal("0");
    private static final BigDecimal ONE = new BigDecimal("1");
    public static final String TRUE = "WIN";
    public static final String FALSE = "FAIL";
    private String val;

    public Univar() {}

    public Univar(String s) {
        val = s;
    }

    public Univar(boolean b) {
        val = (b ? TRUE : FALSE);
    }

    public Univar(BigDecimal bd) {
        val = bd.toString();
    }

    public String strVal() {
        if (val == null)
            throw new NoobAccessException();
        return val;
    }

    public boolean boolVal() {
        if (val == null) return false;
        String upVal = val.toUpperCase();
        if (FALSE.equals(upVal)) return false;
        if (TRUE.equals(upVal)) return true;
        try {
            return !(new BigDecimal(val).compareTo(ZERO) == 0);
        } catch (NumberFormatException e) {
            return true;
        }
    }

    public BigDecimal numVal() {
        if (val == null) 
            throw new NoobAccessException();
        String upVal = val.toUpperCase();
        if (upVal.equals(FALSE)) return ZERO;
        if (upVal.equals(TRUE)) return ONE;
        try {
            return new BigDecimal(val);
        } catch (NumberFormatException e) {
            return ZERO;
        }
    }

    public boolean isNoob() {
        return (val == null);
    }
}
