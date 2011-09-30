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
* Copyright (c) 2006 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Mar 28, 2006
*/
package com.twentysix20.mtg.data.pt;

import com.twentysix20.util.StringUtil;

public class PTNum {
    public static final String VALID_PT = "(\\-?[0-9]+)([\\-\\+]\\*)?|\\*|L";

    int num = 0;
    boolean variable = false;
    boolean loyalty = false;

    public PTNum(int n, boolean v) {
        variable = v;
        num = n;
    }
    
    public PTNum(String s) {
        if (StringUtil.isEmpty(s)) throw new IllegalArgumentException("Can't create PTNum from blank or null.");
        if (!s.matches(VALID_PT)) throw new IllegalArgumentException("'"+s+"' is an invalid PT number.");
        if ("*".equals(s))
            s = "0+*";
        if ("L".equals(s)) {
            s = "0";
            loyalty = true;
        }
        variable = StringUtil.contains(s,"*");
        boolean minus = StringUtil.contains(s,"-");
        String s2 = s.replaceAll("[+*\\-]","").trim();
        try {
            num = Integer.parseInt(s2);
        } catch (Exception e) {
            throw new IllegalArgumentException("Can't create a PTNum from string '"+s+"'.");
        }
        if (minus)
            num = -num;
    }

    public String toString() {
        if (loyalty)
            return "L";
        else if (variable)
            if (num < 0)
                return (-num)+"-*";
            else if (num == 0)
                return "*";
            else
                return num+"+*";
        else
            return ""+num;
    }
    
    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof PTNum)) throw new IllegalArgumentException("Can't check equals against an object that isn't a PTNum.");
        return this.toString().equals(other.toString());
    }
}
