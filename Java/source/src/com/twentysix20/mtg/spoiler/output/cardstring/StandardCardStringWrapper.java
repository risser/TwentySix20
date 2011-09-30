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

import com.twentysix20.mtg.data.card.StandardCard;
import com.twentysix20.util.StringUtil;

public class StandardCardStringWrapper implements CardStringWrapper {
    private StandardCard card;

    public StandardCardStringWrapper(StandardCard card) {
        this.card = card;
    }

    public String cost() {
        return card.getCost().toString();
    }

    public String convertedCost() {
        return ""+card.getCost().convertedCost();
    }

    public String name() {
        return card.getName();
    }

    public String typeline() {
        return card.getType().getTypeLine();
    }

    public String type() {
        return card.getType().getTypes().toString(" ");
    }

    public String supertype() {
        return card.getType().getSupertypes().toString(" ");
    }

    public String subtype() {
        return card.getType().getSubtypes().toString(" ");
    }

    public String pt() {
        return card.getPT().toString();
    }

    public String power() {
        String pt = card.getPT().toString();
        return (StringUtil.isEmpty(pt) ? "" : pt.split("/")[0]);
    }

    public String toughness() {
        String pt = card.getPT().toString();
        return (StringUtil.isEmpty(pt) ? "" : pt.split("/")[1]);
    }

    public String ruleText(String lineBreak) {
        return card.getRuleText().toString(lineBreak);
    }

    public String artist() {
        return card.getArtist();
    }

    public String collectorNumber() {
        return card.getCollectorNumber();
    }

    public String flavorText() {
        return card.getFlavorText();
    }

    public String rarity() {
        return card.getRarity().toString();
    }
}
