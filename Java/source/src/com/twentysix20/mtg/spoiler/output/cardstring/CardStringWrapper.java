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
* Created on Jul 12, 2006
*/
package com.twentysix20.mtg.spoiler.output.cardstring;

public interface CardStringWrapper {
    public String cost();
    public String convertedCost();
    public String name();
    public String typeline();
    public String type();
    public String supertype();
    public String subtype();
    public String pt();
    public String power();
    public String toughness();
    public String ruleText(String lineBreak);
    public String artist();
    public String collectorNumber();
    public String flavorText();
    public String rarity();
}
