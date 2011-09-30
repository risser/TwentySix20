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
* Created on Jul 10, 2006
*/
package com.twentysix20.mtg.data.card;

import com.twentysix20.util.StringUtil;

public class OldSplitCard extends SplitCard {

    public OldSplitCard(CardTemplate template) {
        super(template);
    }

    protected void setSides(CardTemplate template) {
        String[] names = splitString("name", template.getName());
        String[] costs = splitString("cost", template.getCost());
        String[] types = splitString("type", template.getType());
        String[] pts = splitString("power/toughness", template.getPT());
        String[] rules = splitString("rules text", template.getRules());

        oracleInfo[LEFT] = new OracleInformation(names[LEFT], costs[LEFT], types[LEFT], pts[LEFT], rules[LEFT]);
        oracleInfo[RIGHT] = new OracleInformation(names[RIGHT], costs[RIGHT], types[RIGHT], pts[RIGHT], rules[RIGHT]);
    }

    private String[] splitString(String field, String str) {
        if (StringUtil.isEmpty(str))
            return new String[2];
        else if (StringUtil.contains(str, "//"))
            return str.split("//");
        else
            throw new IllegalArgumentException("Value of "+field+" has no split slashes: <"+str+">");
    }
}
