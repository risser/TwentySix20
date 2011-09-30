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
* Created on Jul 9, 2006
*/
package com.twentysix20.mtg.data.card;

import com.twentysix20.mtg.data.mana.ManaCost;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.util.StringList;

public interface OracleInterface {
    public ManaCost getCost();
    public String getName();
    public Type getType();
    public PowerToughness getPT();
    public StringList getRuleText();
    public String getRuleText(int i);
}
