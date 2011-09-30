/*
 * TextParser.java
 *
 * Created on September 4, 2004, 8:06 AM
 */

package com.twentysix20.mtg.old.spoiler.parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import com.twentysix20.mtg.old.spoiler.cardinfo.CardInfo;
import com.twentysix20.mtg.old.spoiler.cardinfo.SpoilerCardInfo;
import com.twentysix20.util.StringList;
import com.twentysix20.util.StringUtil;

/**
name=
color=
cost=
type=
pt=
rules=
flavor=
artist=
rarity=
number=
 * @author  Peter Risser
 */
public class SpoilerTextParser extends AbstractParser {
    StringList attributeNames;
    HashMap attributeMap;
    String recentKey;
    CardInfo currentCard;

    public SpoilerTextParser(String name) throws IOException {
        super(name);
    }
    
    protected BufferedReader getReader(String name) throws IOException {
        return new BufferedReader(new FileReader(name));
    }

    protected void initialize() {
        attributeNames = new StringList();
        attributeMap = new HashMap();
    }

    protected void readInitializationInformation() throws IOException {
        String line;
        while (!StringUtil.isEmpty(line = inFile.readLine())) {
            String[] part = line.trim().split("=");
            String name = part[0].trim();
            String value = part[1].trim();
            if ("set".equals(name))
                setSet(value);
            else if ("pic".equals(name))
                setPicPage(value);
            else {
                if (!CardInfo.attributeSetter.containsKey(name)) 
                    throw new IllegalArgumentException ("Bad attribute name ("+name+") in line '"+line+"'.");
                attributeMap.put(value,name);
                attributeNames.add(value);
            }
        }
    }

    protected void parseLine(String line) {
        line = line.trim();
        if ("".equals(line))
            handleBlankLine();
        else {
            String key = null;
            for (int i = 0; i < attributeNames.size(); i++)
                if (line.indexOf(attributeNames.get(i)) == 0)
                    key = attributeNames.get(i);
            if (key != null) {
                line = line.substring(key.length());
                if ((line.charAt(0) == ':') || (line.charAt(0) == ' ') || (line.charAt(0) == '\t')) {
                    recentKey = key;
                    line = line.trim();
                    if (line.charAt(0) == ':')
                        line = line.substring(1).trim();
                }
                line = line.trim();
            }
            handleAttributeLine(recentKey, line);
        }
    }
    
    private void handleBlankLine() {
        recentKey = null;
        storeCurrentCard();
        currentCard = null;
    }
    
    protected void lastCard() {
        storeCurrentCard();
    }

    private void storeCurrentCard() {
        if ((currentCard != null) && !StringUtil.isEmpty(currentCard.getName()))
            this.storeCard(currentCard);
    }
    
    private void handleAttributeLine(String key, String value) {
        if (StringUtil.isEmpty(key)) return;
        if (currentCard == null) {
            currentCard = new SpoilerCardInfo();
            currentCard.setSetName(getSet());
        }
        
        String attr = (String)attributeMap.get(key);
        currentCard.setAttribute(attr, value);
    }
    
    public static void main(String args[]) throws Exception {
		SpoilerTextParser t = new SpoilerTextParser("/Risser/MTG/spoilers/invasion_spoiler_en_alpha.txt"); //test.txt
	}
}
/*

Card Name:	Manacles of Decay	
Card Color:	W	
Mana Cost:	1W	
Type & Class: 	Enchant Creature 	
Pow/Tou:		
Card Text:	Enchanted creature can't attack.[B]: Enchanted creature gets 
		-1/-1 until end of turn.[R]: Enchanted creature can't block 
		this turn.
Flavor Text:		
Artist:		Gary Ruddell	
Rarity:		C	
Card #:		14/143

*/
