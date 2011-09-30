/*
 * HtmlCardInfo.java
 *
 * Created on September 28, 2004, 9:46 AM
 */

package com.twentysix20.mtg.old.spoiler.cardinfo;

import com.twentysix20.util.StringList;

/**
 *
 * @author  tpnr007
 */
public class HtmlCardInfo extends CardInfo {
    
    private StringList rulesText = new StringList();

    public StringList getRules() {
        return rulesText;
    }
    
    public void setRules(String value) {
        String[] lines = value.split("<br>");
        rulesText.addAll(lines);
    }    

    public Object clone() {
        HtmlCardInfo card = (HtmlCardInfo)super.clone();
        card.rulesText = (StringList)this.rulesText.clone();
        return card;
    }    
}
