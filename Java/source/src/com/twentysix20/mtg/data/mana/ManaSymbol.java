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
* Created on May 1, 2006
*/
package com.twentysix20.mtg.data.mana;

public class ManaSymbol {
    public boolean equals(Object o) {
        if (o == null) return false;
        if (!(o instanceof ManaSymbol)) throw new IllegalArgumentException("Can only test equality of other ManaSymbols");
        return o.toString().equals(this.toString());
    }
 }
