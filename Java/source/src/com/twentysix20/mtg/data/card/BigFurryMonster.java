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
* Created on Jul 5, 2006
*/
package com.twentysix20.mtg.data.card;

import com.twentysix20.util.StringUtil;

public class BigFurryMonster extends CardTemplate {
    private String name = "B.F.M. (Big Furry Monster)";
    private String type = "Creature - The-Biggest-Baddest-Nastiest-Scariest-Creature-You'll-Ever-See";
    private String cost = "BBBBBBBBBBBBBBB";
    private String pt = "99/99";
    private String artist = "Douglas Shuler";
    private String rules = "You must play both B.F.M. cards to put B.F.M. into play. If either B.F.M. card leaves play, sacrifice the other.\nB.F.M. can be blocked only by three or more creatures.";
    private String flavor = "\"It was big. Really, really big. No, bigger than that. Even bigger. Keep going. More.  No, more. Look, we're talking krakens and dreadnoughts for jewelry. It was big!\" --Arna Kennerüd, skyknight";
    private String number = "28";
    private String rarity = "R";

    public String getArtist() {
        return artist;
    }
    public void setArtist(String artist) {
        this.artist = artist;
    }
    public String getCost() {
        return cost;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public String getFlavor() {
        return flavor;
    }
    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getNumber() {
        return number;
    }
    public void setNumber(String number) {
        this.number = number;
    }
    public String getPT() {
        return pt;
    }
    public void setPT(String pt) {
        this.pt = pt;
    }
    public String getRarity() {
        return rarity;
    }
    public void setRarity(String rarity) {
        this.rarity = rarity;
    }
    public String getRules() {
        return rules;
    }
    public void setRules(String rules) {
        this.rules = rules;
    }
    public void addRule(String newRule) {
        if (rules == null)
            rules = "";
        if (!StringUtil.isEmpty(rules))
            rules += "\n";
        rules += newRule;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
}
