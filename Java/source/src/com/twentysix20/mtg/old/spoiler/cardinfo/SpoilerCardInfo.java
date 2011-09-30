/*
 * SpoilerCardInfo.java
 *
 * Created on September 28, 2004, 9:50 AM
 */

package com.twentysix20.mtg.old.spoiler.cardinfo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;

import com.twentysix20.util.StringList;
import com.twentysix20.util.StringUtil;

/**
 *
 * @author  tpnr007
 */
public class SpoilerCardInfo extends CardInfo {
    
    private StringBuffer rulesText = new StringBuffer();

    public void setRules(String value)
	{
        if (StringUtil.isEmpty(value)) return;
        if (rulesText.length() > 0) rulesText.append(" ");
		rulesText.append(value.replaceAll("[\\[\\]]","").trim());
        if ((value.length() < 45) && (value.charAt(value.length()-1) == '.'))
            rulesText.append("%");
    }

    public StringList getRules() {
        StringBuffer buf = new StringBuffer(rulesText.toString().trim());
        HashMap quoteHolder = new HashMap();
        
        int qCount = 0;
        while (buf.indexOf("(") > -1) {
            int start = buf.indexOf("(");
            int end = buf.indexOf(")",start+1) + 1;
            if (end == -1) throw new IllegalArgumentException("Unmatching paren: "+buf);
            String q = buf.substring(start,end);
            String marker = "@"+qCount+"@";
            quoteHolder.put(marker, q);
            buf.replace(start, end, marker+"%");
            qCount++;
        }
        while (buf.indexOf("\"") > -1) {
            int start = buf.indexOf("\"");
            int end = buf.indexOf("\"",start+1) + 1;
            if (end == 0) {
                if (start == buf.length()-1) {
                    buf.deleteCharAt(start); // some onslaught items had an extra quote at the end
                    continue;
                } else {
                    throw new IllegalArgumentException("Unmatching quote: "+buf);
                }
            }
            String q = buf.substring(start,end);
            String marker = "@"+qCount+"@";
            quoteHolder.put(marker, q);
            buf.replace(start, end, marker);
            qCount++;
        }

        Matcher m_cost = COST_PATTERN.matcher(buf.toString());
        buf = new StringBuffer();
        while (m_cost.find()) {
            m_cost.appendReplacement(buf, "%$0");
        }
        m_cost.appendTail(buf);
        
        Matcher m_abilityword = ABILITYWORD_PATTERN.matcher(buf.toString());
        buf = new StringBuffer();
        while (m_abilityword.find()) {
            m_abilityword.appendReplacement(buf, "$1%$3%");
        }
        m_abilityword.appendTail(buf);
        
        Matcher m_keyword = KEYWORD_PATTERN.matcher(buf.toString());
        buf = new StringBuffer();
        while (m_keyword.find()) {
            m_keyword.appendReplacement(buf, "%$0");
        }
        m_keyword.appendTail(buf);
        
        Matcher m_drawcard = DRAWCARD_PATTERN.matcher(buf.toString());
        buf = new StringBuffer();
        while (m_drawcard.find()) {
            m_drawcard.appendReplacement(buf, "$1%$3%");
        }
        m_drawcard.appendTail(buf);
        
        Matcher m_powerword = POWERWORD_PATTERN.matcher(buf.toString());
        buf = new StringBuffer();
        while (m_powerword.find()) {
            m_powerword.appendReplacement(buf, "$1%$3%");
        }
        m_powerword.appendTail(buf);
        
        Iterator qi = quoteHolder.keySet().iterator();
        while (qi.hasNext()) {
            String marker = qi.next().toString();
            String quote = quoteHolder.get(marker).toString();
            int start = buf.indexOf(marker);
            buf.replace(start, start + marker.length(), quote);
        }

        Matcher m_percent = PERCENT_PATTERN.matcher(buf.toString());
        buf = new StringBuffer();
        while (m_percent.find()) {
            m_percent.appendReplacement(buf, "%");
        }
        m_percent.appendTail(buf);
        
        if (buf.length() > 0) {
            if (buf.charAt(0) == '%') buf.deleteCharAt(0);
            if (buf.charAt(buf.length()-1) == '%') buf.deleteCharAt(buf.length()-1);
        }
        
        return new StringList(buf.toString().split("%"));
    }

    public Object clone() {
        SpoilerCardInfo card = (SpoilerCardInfo)super.clone();
        card.rulesText = new StringBuffer(this.rulesText.toString());
        return card;
    }    
}
