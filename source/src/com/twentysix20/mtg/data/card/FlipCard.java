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

import com.twentysix20.mtg.MtgConstants;
import com.twentysix20.mtg.data.mana.ManaCost;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.util.StringList;
import com.twentysix20.util.StringUtil;

public class FlipCard extends Card {

    private static final String LINE_BREAK = "(\n|<br/?>)";
    public static final int NORMAL = 0;
    public static final int FLIPPED = 1;

    protected OracleInterface[] oracleInfo = new OracleInterface[2];

    public FlipCard(CardTemplate template) {
        super(template);

        String[] text = template.getRules().split(LINE_BREAK+"?-----"+LINE_BREAK+"?");
        oracleInfo[NORMAL] = new OracleInformation(template.getName(), template.getCost(), 
                template.getType(), template.getPT(), text[NORMAL]);

        String[] fliptext = text[FLIPPED].split(LINE_BREAK);
        if (StringUtil.contains(fliptext[0],"Penguin")) {
            // handle Curse of the Fire Penguin specifically
            oracleInfo[FLIPPED] = new OracleInformation("", "", fliptext[0], fliptext[3], fliptext[1]+"\n"+fliptext[2]);            
        } else {
            String name = fliptext[0];
            String type = fliptext[1];
            boolean isCreature = StringUtil.contains(type, MtgConstants.TYPE_CREATURE);
            String pt = (isCreature ? fliptext[2] : "");
            String rules = reconcat(isCreature ? 3 : 2, fliptext);
            oracleInfo[FLIPPED] = new OracleInformation(name, "", type, pt, rules);            
        }
    }

    private String reconcat(int start, String[] fliptext) {
        StringBuffer buf = new StringBuffer();
        for (int i = start; i < fliptext.length; i++)
            buf.append(fliptext[i]).append("\n"); // yes, this leaves a trailing \n, but it should be ignored
        return buf.toString();
    }

    public ManaCost getCost() {
        return oracleInfo[NORMAL].getCost();
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
        if ((side != NORMAL) && (side != FLIPPED))
            throw new IllegalArgumentException("Side must be 0 (NORMAL) or 1 (FLIPPED).");
    }

    public boolean isValid() {
        return true;
    }
}
