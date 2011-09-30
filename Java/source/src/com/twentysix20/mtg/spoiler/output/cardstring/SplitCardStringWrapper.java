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

import com.twentysix20.mtg.data.card.SplitCard;
import com.twentysix20.util.StringUtil;

public class SplitCardStringWrapper implements CardStringWrapper {
    private SplitCard card;
    public SplitCardStringWrapper(SplitCard card) {
        this.card = card;
    }

    public String cost() {
        return combine(card.getCost(SplitCard.LEFT).toString(), card.getCost(SplitCard.RIGHT).toString());
    }

    public String convertedCost() {
        return card.getCost(SplitCard.LEFT).convertedCost()+card.getCost(SplitCard.RIGHT).convertedCost()+"";
    }

    private String combine(String left, String right) {
        String a = left;
        if (!(StringUtil.isEmpty(left) || StringUtil.isEmpty(right)))
            a += " // ";
        a += right;
        return a;
    }

    public String name() {
        return combine(card.getName(SplitCard.LEFT),card.getName(SplitCard.RIGHT));
    }

    public String type() {
        return combine(card.getType(SplitCard.LEFT).getTypes().toString(" "),card.getType(SplitCard.RIGHT).getTypes().toString(" "));
    }

    public String subtype() {
        return combine(card.getType(SplitCard.LEFT).getSubtypes().toString(" "),card.getType(SplitCard.RIGHT).getSubtypes().toString(" "));
    }

    public String supertype() {
        return combine(card.getType(SplitCard.LEFT).getSupertypes().toString(" "),card.getType(SplitCard.RIGHT).getSupertypes().toString(" "));
    }

    public String typeline() {
        return combine(card.getType(SplitCard.LEFT).toString(),card.getType(SplitCard.RIGHT).toString());
    }

    public String pt() {
        return combine(card.getPT(SplitCard.LEFT).toString(),card.getPT(SplitCard.RIGHT).toString());
    }

    public String power() {
        String pt = combine(card.getPT(SplitCard.LEFT).toString(),card.getPT(SplitCard.RIGHT).toString());
        return (StringUtil.isEmpty(pt) ? "" : pt.split("/")[0]);
    }

    public String toughness() {
        String pt = combine(card.getPT(SplitCard.LEFT).toString(),card.getPT(SplitCard.RIGHT).toString());
        return (StringUtil.isEmpty(pt) ? "" : pt.split("/")[1]);
    }

    public String ruleText(String lineBreak) {
        return combine(card.getRuleText(SplitCard.LEFT).toString(lineBreak),card.getRuleText(SplitCard.RIGHT).toString(lineBreak));
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