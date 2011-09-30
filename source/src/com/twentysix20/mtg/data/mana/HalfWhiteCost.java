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
* Created on Apr 28, 2006
*/
package com.twentysix20.mtg.data.mana;

import com.twentysix20.mtg.MtgConstants;

public class HalfWhiteCost extends ManaCost {
    public static final String HALF_WHITE_ORACLE = "W(HALF)";

    private static HalfWhiteCost self = new HalfWhiteCost();
    public static HalfWhiteCost getInstance() {
        return self;
    }
    
    public String toString() {
        return MtgConstants.SYMBOL_HALF_WHITE;
    }
    
    public boolean equals(Object other) {
        return (other instanceof HalfWhiteCost);
    }

}
