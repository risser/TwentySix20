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
* Created on Jun 22, 2006
*/
package com.twentysix20.mtg.data.card;

import com.twentysix20.mtg.data.mana.ManaCost;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.util.StringList;

public class StandardCard extends Card {

    protected OracleInterface oracleInfo;

    public StandardCard(CardTemplate template) {
        super(template);
        oracleInfo = new OracleInformation(template);
    }

    public ManaCost getCost() {
        return oracleInfo.getCost();
    }
    public String getName() {
        return oracleInfo.getName();
    }
    public Type getType() {
        return oracleInfo.getType();
    }
    public PowerToughness getPT() {
        return oracleInfo.getPT();
    }
    public StringList getRuleText() {
        return oracleInfo.getRuleText();
    }
    public String getRuleText(int i) {
        return oracleInfo.getRuleText(i);
    }

    public boolean isValid() {
        return true;
    }
}
