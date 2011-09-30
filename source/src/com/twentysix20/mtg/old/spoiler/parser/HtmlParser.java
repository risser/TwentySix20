/*
 * TextParser.java
 *
 * Created on September 4, 2004, 8:06 AM
 */

package com.twentysix20.mtg.old.spoiler.parser;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.twentysix20.mtg.old.spoiler.cardinfo.CardInfo;
import com.twentysix20.mtg.old.spoiler.cardinfo.HtmlCardInfo;
import com.twentysix20.mtg.old.spoiler.output.XmlPrintedOutput;
import com.twentysix20.util.StringUtil;

/**
 * @author  Peter Risser
 */
public class HtmlParser extends AbstractParser {
//    StringList attributeNames;
//    HashMap attributeMap;
//    String recentKey;
    CardInfo currentCard;
    int tableCount = 0;
    int column = 0;
    String[] attributes;
    String currentPower;

    public HtmlParser(String name) throws IOException {
        super(name);
    }
    
    protected BufferedReader getReader(String name) throws IOException {
        return new BufferedReader(new InputStreamReader(new FileInputStream(name),"UTF-8"));
    }
    
    protected void initialize() {
        attributes = new String[15];
    }

    protected void readInitializationInformation() throws IOException {
/*        
        String line;
        while (!StringUtil.isEmpty(line = inFile.readLine())) {
            String[] part = line.trim().split("=");
            String name = part[0].trim();
            String value = part[1].trim();
            if ("set".equals(name))
                setSet(value);
            else {
                if (!CardInfo.attributeSetter.containsKey(name)) 
                    throw new IllegalArgumentException ("Bad attribute name ("+name+") in line '"+line+"'.");
                attributeMap.put(value,name);
                attributeNames.add(value);
            }
        }
 */
    }

    private boolean pastHeader() {
        return (tableCount > 6);
    }
    protected void parseLine(String line) {
//System.out.println(line);
        line = line.replaceAll("<td class=\"small\">","<td>");
        if (line.indexOf("<table")  > -1) {
            tableCount++;
        } else if (line.indexOf("\"headline\"") > -1) {
            int ipos = line.indexOf("<i>") + 3;
            int iend = line.indexOf("</i>");
            String[] ss = line.substring(ipos,iend).split(":");
            setSet(ss[0]);
            setPicPage(ss[(ss.length > 1) ? 1 : 0]);
        } else if (line.indexOf("tablesort") > -1) {
            int pos = line.indexOf("tablesort");
            int pos2 = line.indexOf("=",pos);
            int qpos = line.indexOf("\"",pos2);
            String line2 = line.substring(pos2+1,qpos).trim();
            int i = Integer.parseInt(line2);
            int bpos = line.indexOf("<b>") + 3;
            int bend = line.indexOf("</b>");
            String title = line.substring(bpos,bend).trim();
            
            if (title.indexOf("Cost") > -1)
                attributes[i] = "cost";
            else if (title.indexOf("Flavor") > -1)
                attributes[i] = "flavor";
            else if (title.indexOf("Color") > -1)
                attributes[i] = "color";
            else if (title.indexOf("Artist") > -1)
                attributes[i] = "artist";
            else if (title.indexOf("Rarity") > -1)
                attributes[i] = "rarity";
            else if (title.indexOf("Type") > -1)
                attributes[i] = "type";
            else if (title.indexOf("Name") > -1)
                attributes[i] = "name";
            else if (title.indexOf("Rules") > -1)
                attributes[i] = "rules";
            else if (title.indexOf("Number") > -1)
                attributes[i] = "number";
            else if (title.indexOf("#") > -1)
                attributes[i] = "number";
            else if (title.indexOf("P/T") > -1)
                attributes[i] = "pt";
            else if (title.equals("P") || title.equals("T"))
                attributes[i] = title;
        } else if (line.indexOf("</tr>") > -1) {
            storeCurrentCard();
            currentCard = null;
            column = 0;
        } else if (line.indexOf("<td>") > -1) {
            handleAttributeLine(line);
        }
    }

    private void storeCurrentCard() {
        if ((currentCard != null) && !StringUtil.isEmpty(currentCard.getName()))
            this.storeCard(currentCard);
    }
    
    protected void lastCard() {
        storeCurrentCard();
    }

/*        
    private void handleBlankLine() {
        recentKey = null;
        if ((currentCard != null) && !StringUtil.isEmpty(currentCard.getName()))
            this.storeCard(currentCard);
        currentCard = null;
    }
*/    
    private void handleAttributeLine(String line) {
        column++;
        if (currentCard == null) {
            currentCard = new HtmlCardInfo();
            currentCard.setSetName(getSet());
        }
        line = line.replaceAll("</?nobr>","");
        line = line.replaceAll("<a.*?>","");
        line = line.replaceAll("</?[iba]>","");
        line = line.replaceAll("<img[^>]*White or Blue Mana[^<]*\">","[WU]");
        line = line.replaceAll("<img[^>]*White or Black Mana[^<]*\">","[WB]");
        line = line.replaceAll("<img[^>]*Red or White Mana[^<]*\">","[RW]");
        line = line.replaceAll("<img[^>]*Green or White Mana[^<]*\">","[GW]");
        line = line.replaceAll("<img[^>]*Blue or Black Mana[^<]*\">","[UB]");
        line = line.replaceAll("<img[^>]*Blue or Red Mana[^<]*\">","[UR]");
        line = line.replaceAll("<img[^>]*Blue or Green Mana[^<]*\">","[UG]");
        line = line.replaceAll("<img[^>]*Black or Green Mana[^<]*\">","[BG]");
        line = line.replaceAll("<img[^>]*Black or Red Mana[^<]*\">","[BR]");
        line = line.replaceAll("<img[^>]*Red or Green Mana[^<]*\">","[RG]");
        line = line.replaceAll("<img[^>]*Red Mana[^<]*\">","R");
        line = line.replaceAll("<img[^>]*Blue Mana[^<]*\">","U");
        line = line.replaceAll("<img[^>]*Black Mana[^<]*\">","B");
        line = line.replaceAll("<img[^>]*Green Mana[^<]*\">","G");
        line = line.replaceAll("<img[^>]*White Mana[^<]*\">","W");
        line = line.replaceAll("<img[^>]*X Mana[^<]*\">","X");
        line = line.replaceAll("<img[^>]*alt=\"Tap\"[^<]*\">","Tap");
        for (int i = 0; i < 17; i++)
            line = line.replaceAll("<img[^>]*"+i+" Mana[^<]*\">",""+i);
        int tpos = line.indexOf("<td>") + 4;
        int tend = line.indexOf("</td>");
        if (tend >= tpos) {
            line = line.substring(tpos,tend);
System.out.println(line);            
            if (attributes[column].equals("P"))
                currentPower = line;
            else if (attributes[column].equals("T"))
                currentCard.setAttribute("pt", currentPower + "/" + line);
            else
                currentCard.setAttribute(attributes[column], line);
        }
/*
        String attr = (String)attributeMap.get(key);
        currentCard.setAttribute(attr, value);
 */
    }
    
    public static void main(String args[]) throws Exception {
		HtmlParser t = new HtmlParser("/Risser/MTG/spoilers/CHKamigawa.html"); //test.txt
        XmlPrintedOutput x = new XmlPrintedOutput("/Risser/MTG/spoilers/XML/"+t.getSet()+".xml", 
                t.getList());
        x.generateFile();
	}
}
