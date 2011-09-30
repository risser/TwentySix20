/*
 * OracleCardInfo.java
 *
 * Created on September 28, 2004, 9:49 AM
 */

package com.twentysix20.mtg.old.spoiler.cardinfo;

import com.twentysix20.util.StringList;
import com.twentysix20.util.StringUtil;

/**
 *
 * @author  tpnr007
 */
public class OracleCardInfo extends CardInfo {
    
    StringList rulesList = new StringList();
    
    public void setCost (String value) {
        if (!StringUtil.isEmpty(cost))
            throw new IllegalStateException("Can't assign cost ("+value+") for '"+getName()+"' when cost is already set.");
        value = value.toUpperCase().replaceAll("[ \\{\\}\\,]","");
        if (!value.matches("[\\[\\]/WUBRGXYZ0123456789½]*"))
            throw new IllegalArgumentException("Invalid characters in cost ("+value+") for '"+getName()+"'.");
		cost = value;
	}

    public StringList getRules() {
        return rulesList;
    }
    
    public void setRules(String value) {
        value = value.replaceAll("ocT","Tap");
        value = value.replaceAll("\\(o\\[","\\(\\[");
        rulesList.add(value);
    }
    
}
