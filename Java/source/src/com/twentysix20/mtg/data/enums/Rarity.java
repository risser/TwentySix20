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
* Created on Jun 21, 2006
*/
package com.twentysix20.mtg.data.enums;

import com.twentysix20.mtg.MtgConstants;
import com.twentysix20.util.StringUtil;

public class Rarity {

    // Rarity
    static final public Rarity TIMESHIFTED = new Rarity(MtgConstants.RARITY_TIMESHIFTED);
    static final public Rarity MYTHIC = new Rarity(MtgConstants.RARITY_MYTHIC);
    static final public Rarity RARE = new Rarity(MtgConstants.RARITY_RARE);
    static final public Rarity UNCOMMON = new Rarity(MtgConstants.RARITY_UNCOMMON);
    static final public Rarity COMMON = new Rarity(MtgConstants.RARITY_COMMON);
    static final public Rarity LAND = new Rarity(MtgConstants.RARITY_LAND);

    public static final String VALID_RARITY_CODE = "[MRUCLT]";

    private String rarity;

    private Rarity(String r) {
        this.rarity = r;
    }

    public String getText() {
        return rarity;
    }

    public String getAbbrev() {
        return rarity.substring(0,1).toUpperCase();
    }

    public String toString() {
        return getText();
    }

    static public Rarity getRarity(String code) {
        if (StringUtil.isEmpty(code)) throw new IllegalArgumentException("Code can't be blank or null.");
        if (code.length() != 1) throw new IllegalArgumentException("Code must be exactly one letter.");
        switch (code.charAt(0)) {
            case 'M' : return MYTHIC;
            case 'R' : return RARE;
            case 'U' : return UNCOMMON;
            case 'C' : return COMMON;
            case 'L' : return LAND;
            case 'T' : return TIMESHIFTED;
            default : throw new IllegalArgumentException("Code ("+code+") not recognized.");
        }
    }

}