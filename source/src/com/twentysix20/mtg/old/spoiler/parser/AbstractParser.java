/*
 * AbstractParser.java
 *
 * Created on September 4, 2004, 8:08 AM
 */

package com.twentysix20.mtg.old.spoiler.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.twentysix20.mtg.old.spoiler.cardinfo.CardInfo;
import com.twentysix20.util.StringUtil;

/**
 *
 * @author  tpnr007
 */
public abstract class AbstractParser {
    public static final String TEXT_PARSER = "text";
    public static final String HTML_PARSER = "html";

    private String fileName;
    protected BufferedReader inFile;
    protected List cardList = new ArrayList();
    private String set = "";
    private String picPage = "";
    HashMap versionMap = new HashMap();
    int versionCount = 0;

    public AbstractParser(String name) throws IOException {
        fileName = name;
        inFile = getReader(name);
        initialize();
        parse();
    }

    public String getFileName() {
        return fileName;
    }

    public List getList() {
        return cardList;
    }

    protected abstract BufferedReader getReader(String name) throws IOException;
    protected abstract void initialize();
    protected abstract void parseLine(String line);
    protected abstract void readInitializationInformation() throws IOException;
    protected abstract void lastCard();
    
    protected void parse() throws IOException {
        readInitializationInformation();
        String line;
        while ((line = inFile.readLine()) != null) {
            if (StringUtil.contains(line,"ersions)")) {
                int paren = line.lastIndexOf("(");
                String num = line.substring(paren+1,line.indexOf(" ",paren));
                versionCount = Integer.parseInt(num);
                line = line.substring(0,paren);
            }
//System.out.println(line);            
            parseLine(line);
        }
        lastCard();
    }
    
    protected void storeCard(CardInfo card) {
        if (versionCount > 1) {
            versionCount--;
            storeCard((CardInfo)card.clone());
        }
        if (versionMap.containsKey(card.getName()) && StringUtil.isEmpty(card.getFace())) {
            CardInfo oldCard = (CardInfo)versionMap.get(card.getName());
            if (StringUtil.isEmpty(oldCard.getVersion()))
                oldCard.setVersion("1");
            int version = 0;
            try {
                version = Integer.parseInt(oldCard.getVersion()) + 1;
            } catch (NumberFormatException nfe) {
                throw new IllegalStateException("Card '"+oldCard.getName()+"' contains invalid version ("+oldCard.getVersion()+").");
            }
            card.setVersion(Integer.toString(version));
        }
        versionMap.put(card.getName(), card);
        cardList.add(card);
/*        
        System.out.println("***");
        System.out.println(card.getName() + "   {"+card.getCost()+"}");
        System.out.println(card.getPower() + "/" + card.getTough());
        System.out.println("\"" + card.getTypeLine() + "\"   " + card.getPower() + "/" + card.getTough());
        if (!StringUtil.isEmpty(card.getSupertype()))
            System.out.println("^ "+card.getSupertype());
        System.out.print(card.getTypes().toString("[","]\n"));
        System.out.print(card.getSubtypes().toString("(",")\n"));
//        System.out.println(card.getSet() + " " + card.getRarity());
        System.out.println(card.getSetName() + " " + card.getRarity() + " ("+card.getNumber()+")");
        System.out.print("---\n"+card.getRules().toString(">>","<<\n"));
        System.out.println("---\n"+card.getFlavor());
 */
    }
    
    public void setSet(String value) {
        set = value;
    }
    public String getSet() {
        return set;
    }
    
    public void setPicPage(String value) {
        picPage = value;
    }
    public String getPicPage() {
        return picPage;
    }
}