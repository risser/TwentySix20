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

public class PowerToughness {
    public static final String VALID_PT_LINE = "(10|1?[1-9])|("+PTNum.VALID_PT+")/("+PTNum.VALID_PT+")";

    protected PTNum power;
    protected PTNum tough;
    
    protected PowerToughness() {}

    public PowerToughness(String s) {
        if (StringUtil.isEmpty(s)) throw new IllegalArgumentException("Can't create PowerToughness with blank or null string.");
        if (!s.matches(VALID_PT_LINE)) throw new IllegalArgumentException("'"+s+"' is an invalid PowerToughness line.");

        if (s.indexOf('/') < 0)
            s += "/L";
        String[] pt = s.split("/");
        power = new PTNum(pt[0]);
        tough = new PTNum(pt[1]);
    }

    public PowerToughness(String p, String t) {
        power = new PTNum(p);
        tough = new PTNum(t);
    }

    public boolean equals(Object other) {
        if (other == null) return false;
        if (!(other instanceof PowerToughness)) throw new IllegalArgumentException("Can't check equals against an object that isn't a PowerToughness.");
        
        return this.toString().equals(((PowerToughness)other).toString());
    }

    public String toString() {
        return power.toString()+"/"+tough.toString();
    }
    
    public boolean hasNoPowerToughness() {
        return (this instanceof NoPowerToughness);
    }
    
    public static NoPowerToughness noPowerToughness() {
        return NoPowerToughness.getInstance();
    }

}
