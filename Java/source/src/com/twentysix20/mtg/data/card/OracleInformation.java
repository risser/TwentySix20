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
import com.twentysix20.mtg.data.mana.ManaCostFactory;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.util.StringList;
import com.twentysix20.util.StringUtil;

public class OracleInformation implements OracleInterface {
    protected String name;
    protected ManaCost cost = ManaCost.costless();
    protected Type type = Type.typeless();
    protected PowerToughness pt = PowerToughness.noPowerToughness();
    protected StringList ruleText = new StringList();
    
    public OracleInformation(CardTemplate template) {
        this(template.getName(), template.getCost(), template.getType(),
                template.getPT(), template.getRules());
    }
    public OracleInformation(String name, String cost, String type, String pt, String ruleText) {
        setName(name.trim());
        setCost(StringUtil.isEmpty(cost) ? ManaCost.costless() : ManaCostFactory.create(cost.trim())); 
        setType(StringUtil.isEmpty(type) ? Type.typeless() : new Type(type.trim()));
        setPT(StringUtil.isEmpty(pt) ? PowerToughness.noPowerToughness() : new PowerToughness(pt.trim()));
        
        String[] rules = ruleText.split("\n|<br/?>");
        for (int i = 0; i < rules.length; i++)
            if (!StringUtil.isEmpty(rules[i]))
                addRuleText(rules[i].trim());
    }
    public OracleInformation(String name, ManaCost cost, Type type, PowerToughness pt, StringList ruleText) {
        setName(name);
        setCost(cost);
        setType(type);
        setPT(pt);
        setRuleText(ruleText);
    }
    
    public ManaCost getCost() {
        return cost;
    }
    public String getName() {
        return name;
    }
    public PowerToughness getPT() {
        return pt;
    }
    public StringList getRuleText() {
        return ruleText;
    }
    public String getRuleText(int i) {
        return ruleText.get(i);
    }
    public Type getType() {
        return type;
    }
    protected void setCost(ManaCost cost) {
        this.cost = cost;
    }
    protected void setName(String name) {
        this.name = name;
    }
    protected void setPT(PowerToughness pt) {
        this.pt = pt;
    }
    protected void setRuleText(StringList ruleText) {
        this.ruleText = ruleText;
    }
    protected void addRuleText(String line) {
        if (ruleText == null)
            ruleText = new StringList();
        ruleText.add(line);
    }
    protected void setType(Type type) {
        this.type = type;
    }
}
