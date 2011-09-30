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

public class NoPowerToughness extends PowerToughness {
    private static NoPowerToughness self = new NoPowerToughness();
    public static NoPowerToughness getInstance() {
        return self;
    }
    
    public String toString() {
        return "";
    }
    
    public boolean equals(Object other) {
        return (other instanceof NoPowerToughness);
    }
}
