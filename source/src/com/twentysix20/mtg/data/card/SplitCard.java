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
* Created on Jul 11, 2006
*/
package com.twentysix20.mtg.data.card;

import com.twentysix20.mtg.data.mana.ManaCost;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.util.StringList;

public abstract class SplitCard extends Card {

    public static final int LEFT = 0;
    public static final int RIGHT = 1;

    protected OracleInterface[] oracleInfo = new OracleInterface[2];

    public SplitCard(CardTemplate template) {
        super(template);
        setSides(template);
    }

    abstract protected void setSides(CardTemplate template);

    public ManaCost getCost(int side) {
        validateSide(side);
        return oracleInfo[side].getCost();
    }
    public String getName(int side) {
        validateSide(side);
        return oracleInfo[side].getName();
    }
    public Type getType(int side) {
        validateSide(side);
        return oracleInfo[side].getType();
    }
    public PowerToughness getPT(int side) {
        validateSide(side);
        return oracleInfo[side].getPT();
    }
    public StringList getRuleText(int side) {
        validateSide(side);
        return oracleInfo[side].getRuleText();
    }
    public String getRuleText(int side, int i) {
        validateSide(side);
        return oracleInfo[side].getRuleText(i);
    }

    private void validateSide(int side) {
        if ((side != LEFT) && (side != RIGHT))
            throw new IllegalArgumentException("Side must be 0 (LEFT) or 1 (RIGHT).");
    }

    public boolean isValid() {
        return true;
    }
}
