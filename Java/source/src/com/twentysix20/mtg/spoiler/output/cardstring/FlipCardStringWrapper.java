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

import com.twentysix20.mtg.data.card.FlipCard;
import com.twentysix20.util.StringUtil;

public class FlipCardStringWrapper implements CardStringWrapper {
    private FlipCard card;
    public FlipCardStringWrapper(FlipCard card) {
        this.card = card;
    }

    public String cost() {
        return card.getCost().toString();
    }

    public String convertedCost() {
        return ""+card.getCost().convertedCost();
    }

    public String name() {
        return card.getName(FlipCard.NORMAL);
    }

    public String typeline() {
        return card.getType(FlipCard.NORMAL).toString();
    }

    public String type() {
        return card.getType(FlipCard.NORMAL).getTypes().toString(" ");
    }

    public String supertype() {
        return card.getType(FlipCard.NORMAL).getSupertypes().toString(" ");
    }

    public String subtype() {
        return card.getType(FlipCard.NORMAL).getSubtypes().toString(" ");
    }

    public String pt() {
        return card.getPT(FlipCard.NORMAL).toString();
    }

    public String power() {
        String pt = card.getPT(FlipCard.NORMAL).toString();
        return (StringUtil.isEmpty(pt) ? "" : pt.split("/")[0]);
    }

    public String toughness() {
        String pt = card.getPT(FlipCard.NORMAL).toString();
        return (StringUtil.isEmpty(pt) ? "" : pt.split("/")[1]);
    }

    public String ruleText(String lineBreak) {
        return card.getRuleText(FlipCard.NORMAL).toString(lineBreak) + lineBreak 
        + "-----" + lineBreak
        + card.getName(FlipCard.FLIPPED) + lineBreak
        + card.getType(FlipCard.FLIPPED) + lineBreak
        + (card.getPT(FlipCard.FLIPPED).hasNoPowerToughness() ? "" : card.getPT(FlipCard.FLIPPED).toString() + lineBreak)
        + card.getRuleText(FlipCard.FLIPPED).toString(lineBreak);
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
