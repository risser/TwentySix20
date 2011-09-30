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

import com.twentysix20.mtg.data.enums.Rarity;
import com.twentysix20.util.StringUtil;

abstract public class Card {
    public static final String VALID_COLLECTOR_NUMBER = "[1234567890]?[1234567890]?[1234567890][ab]?";

    protected String collectorNumber;
    protected String artist;
    protected String flavorText;
    protected Rarity rarity; // can have null rarity, but shouldn't.
    
    public Card(CardTemplate template) {
        if (!StringUtil.isEmpty(template.getRarity()))
            rarity = Rarity.getRarity(template.getRarity());
        if (!StringUtil.isEmpty(template.getNumber()))
            setCollectorNumber(template.getNumber());
        artist = template.getArtist();
        flavorText = template.getFlavor();
    }
    
    protected void setCollectorNumber(String collectorNumber) {
        if (!StringUtil.isEmpty(this.collectorNumber)) throw new IllegalStateException("Collector Number has already been set for this item.");
        String num = collectorNumber.trim().toLowerCase();
        if (!num.matches(VALID_COLLECTOR_NUMBER) || "000".equals(num)) throw new IllegalArgumentException("Invalid value or format for Collector Number. ("+collectorNumber+")");
        this.collectorNumber = num;
    }

    protected void setCollectorNumber(int collectorNumber) {
        if ((collectorNumber <= 0) || (collectorNumber > 999)) throw new IllegalArgumentException("Collector Number must be greater than 0 and less than 1000. ("+collectorNumber+")");
        this.collectorNumber = Integer.toString(collectorNumber);
    }

    public String getArtist() {
        return artist;
    }

    public String getCollectorNumber() {
        return collectorNumber;
    }

    public String getFlavorText() {
        return flavorText;
    }

    public Rarity getRarity() {
        return rarity;
    }

    abstract public boolean isValid();

}
