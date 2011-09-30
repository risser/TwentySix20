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
* Created on Jun 5, 2006
*/
package com.twentysix20.mtg.spoiler.reader;

//test:
//Oracle reader -> oracle entry
//Oracle reader -> actual card
//HTML reader -> actual card ==> count # is trading number (check brothers yamazuki)
//HTML reader -> oracle entry



import java.io.IOException;

import com.twentysix20.mtg.MtgConstants;
import com.twentysix20.mtg.data.card.*;
import com.twentysix20.mtg.data.enums.Rarity;
import com.twentysix20.mtg.data.mana.ManaCostFactory;
import com.twentysix20.mtg.data.pt.PowerToughness;
import com.twentysix20.mtg.data.type.Type;
import com.twentysix20.util.StringUtil;

public class FileHtmlSpoilerReader extends BaseFileReader {
    private boolean readingCards;
//    private boolean isMidLine;
    private String totalLine;
    private int freeTextColumn;
    private int cardCount;

    public FileHtmlSpoilerReader(String fn) throws IOException {
        super(fn);
        readingCards = false;
        freeTextColumn = 0;
        cardCount = 0;
        totalLine = "";
    }

    public void handleLine(String line) {

        // indicates the start of the card section.  Actually the first line of each card.
        if (StringUtil.contains(line, "autoCardWindow"))
            readingCards = true;
        
        // turn it off at the end of the table
        if (StringUtil.contains(line, "/table"))
            readingCards = false;

        // if we're not reading cards yet, move along
        if (!readingCards) return;

        line = cleanString(line);
        if (StringUtil.contains(line, "<tr")) {
            if (template != null) throw new IllegalStateException("Got a second <tr> before the closing </tr>.");
        }

        boolean doneWithCard = (StringUtil.contains(line, "</tr>"));
        boolean hasOpeningTD = (StringUtil.contains(line, "<td"));
        boolean hasClosingTD = (StringUtil.contains(line, "</td>"));
        line = line.replaceAll("</?t[dr][^<]*>","");
        line = line.replaceAll("&nbsp;"," ");
        line = line.trim();
        if (hasOpeningTD)
            totalLine = line;
        else
            totalLine += " "+line;

        if (hasClosingTD) {
            line = totalLine.trim();
            totalLine = "";
//            if (StringUtil.contains(line, "<td") || isMidLine || hasClosingTD) {
    System.out.println("::"+freeTextColumn+"::"+line+"::");
            if (template == null) {
                resetReader();
                cardCount++;
                template.setName(line);
            } else if (line.toUpperCase().matches(ManaCostFactory.VALID_COST_LINE) && (freeTextColumn == 0)) {
                // make sure we haven't hit the free-text sections.  This means we aren't at Rarity, in case it's a U or R.
                template.setCost(line);
            } else if (!StringUtil.isEmpty(template.getType()) && line.toUpperCase().matches(Rarity.VALID_RARITY_CODE)) {
                getCurrentTemplate().setRarity(line);
            } else if (line.matches(Type.VALID_TYPE_LINE)) {
                template.setType(line);
                if (!StringUtil.contains(template.getType(),MtgConstants.TYPE_CREATURE))
                    freeTextColumn--; // if not creature, P/T will be blank, so we'll need to skip it.
            } else if (line.matches(PowerToughness.VALID_PT_LINE)) {
                template.setPT(line);
            } else if (line.matches(StandardCard.VALID_COLLECTOR_NUMBER) && !"000".equals(line)) {
                getCurrentTemplate().setNumber(line);
            } else if (!StringUtil.isEmpty(template.getType())) {
                freeTextColumn++;
                switch (freeTextColumn) {
                case 0 : break; // probably blank P/T.  Skip it.
                case 1 : // rules
                    if (!line.matches("\\[[WUBRG]\\]") && !StringUtil.isEmpty(line)) { // ignore the basic land [X] abilities & empty rules text
                        String oldLine = template.getRules().trim();
                        template.setRules((StringUtil.isEmpty(oldLine) ? "" : oldLine+" ")+line);
                    }
//                    if (!hasClosingTD) {
//                        freeTextColumn--; // if it's not closed, come back here for more rule-y goodness
//                        isMidLine = true;
//                    }
                    break;
                case 2 : // flavor
                    line = line.replaceAll("<br/?>","\n");
                    if (!StringUtil.isEmpty(line)) {
                        String oldLine = template.getFlavor().trim();
                        template.setFlavor((StringUtil.isEmpty(oldLine) ? "" : oldLine+" ")+line);
                    }
//                    if (!hasClosingTD) {
//                        freeTextColumn--; // if it's not closed, come back here for more rule-y goodness
//                        isMidLine = true;
//                    }
                    break;
                case 3: // artist
                    getCurrentTemplate().setArtist(line);
                    break;
                default:
                    throw new IllegalStateException("Whoa!  Too many free text columns! ("+template.getName()+":" + line + ")");
                }
    //ALSO, flavor & artist & empty </tr> line            
            }
        }

        if (doneWithCard)
            addCurrentEntryToList();
    }

    private void resetReader() {
        template = new CardTemplate();
        freeTextColumn = 0;
        totalLine = "";
//        isMidLine = false;
    }

    protected void addCurrentEntryToList() {
        if ((template != null) && (StringUtil.isEmpty(getCurrentTemplate().getNumber())))
            getCurrentTemplate().setNumber(""+cardCount);
        super.addCurrentEntryToList();
    }

    protected String cleanString(String line) {
        line = line.replaceAll("</?nobr>","");
        line = line.replaceAll("<a.*?>","");
        line = line.replaceAll("</?[iba]>","");
//        line = line.replaceAll("Æ","Ae") // does name parse this already?
        line = line.replaceAll("<img[^>]*White or Blue Mana[^<]*\">","(W/U)");
        line = line.replaceAll("<img[^>]*White or Black Mana[^<]*\">","(W/B)");
        line = line.replaceAll("<img[^>]*Red or White Mana[^<]*\">","(R/W)");
        line = line.replaceAll("<img[^>]*Green or White Mana[^<]*\">","(G/W)");
        line = line.replaceAll("<img[^>]*Blue or Black Mana[^<]*\">","(U/B)");
        line = line.replaceAll("<img[^>]*Blue or Red Mana[^<]*\">","(U/R)");
        line = line.replaceAll("<img[^>]*Blue or Green Mana[^<]*\">","(U/G)");
        line = line.replaceAll("<img[^>]*Black or Green Mana[^<]*\">","(B/G)");
        line = line.replaceAll("<img[^>]*Black or Red Mana[^<]*\">","(B/R)");
        line = line.replaceAll("<img[^>]*Red or Green Mana[^<]*\">","(R/G)");
        line = line.replaceAll("<img[^>]*Two or White Mana[^<]*\">","(2/W)");
        line = line.replaceAll("<img[^>]*Two or Blue Mana[^<]*\">","(2/U)");
        line = line.replaceAll("<img[^>]*Two or Black Mana[^<]*\">","(2/B)");
        line = line.replaceAll("<img[^>]*Two or Red Mana[^<]*\">","(2/R)");
        line = line.replaceAll("<img[^>]*Two or Green Mana[^<]*\">","(2/G)");
        line = line.replaceAll("<img[^>]*Snow[^<]*\">","Snow");
        line = line.replaceAll("<img[^>]*Red Mana[^<]*\">","R");
        line = line.replaceAll("<img[^>]*Blue Mana[^<]*\">","U");
        line = line.replaceAll("<img[^>]*Black Mana[^<]*\">","B");
        line = line.replaceAll("<img[^>]*Green Mana[^<]*\">","G");
        line = line.replaceAll("<img[^>]*White Mana[^<]*\">","W");
        line = line.replaceAll("<img[^>]*X Mana[^<]*\">","X");
        line = line.replaceAll("<img[^>]*alt=\"Untap\"[^<]*\">","Untap");
        line = line.replaceAll("<img[^>]*alt=\"Tap\"[^<]*\">","Tap");
        for (int i = 0; i < 17; i++)
            line = line.replaceAll("<img[^>]*"+i+" Mana[^<]*\">",""+i);
        line = super.cleanString(line);
        return line;
    }

    public Card createEntryFromTemplate() {
        return CardFactory.create(template);
    }
}

/*
 * 
public class FileHtmlSpoilerReader implements OracleReader, FileScannerListener {
    private String fileName;
    private FileScanner filescan;
    private List entryList = new ArrayList();
    private OracleEntry currentEntry;
    private boolean on = false;
    private int column = 0;
//    private String[] attributes;

    public FileHtmlSpoilerReader(String fn) throws IOException {
        super();
        fileName = fn;
        filescan = new FileScanner(fileName);
        filescan.addListener(this);
        filescan.scanFile();
    }

    public List getEntryList() {
        return entryList;
    }

    public void handleLine(String line) {
        line = line.replaceAll("<td class=\"small\">","<td>");
        if (!on) {
            if (line.indexOf("<h2") >= 0) {
                on = true;
//                int ipos = line.indexOf("<i>") + 3;
//                int iend = line.indexOf("</i>");
//                String[] ss = line.substring(ipos,iend).split(":");
//                setSet(ss[0]);
            }
        } else {
            if (line.indexOf("</tr>") > -1) {
                addCurrentEntryToList();
                column = 0;
            } else if ((line.indexOf("tablesort") == -1) && (line.indexOf("<td>") > -1)) {
                handleAttributeLine(line);
            }
        }
    }

    private void addCurrentEntryToList() {
        if (currentEntry != null)
            entryList.add(currentEntry);
        currentEntry = null;
    }

    public void scanComplete() {
        addCurrentEntryToList();
    }

    private void handleAttributeLine(String line) {
        column++;
        if (currentEntry == null) {
            currentEntry = new OracleEntry();
        }
        line = line.replaceAll("</?nobr>","");
        line = line.replaceAll("<a.*?>","");
        line = line.replaceAll("</?[iba]>","");
//        line = line.replaceAll("Æ","Ae") // does name parse this already?
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
//System.out.println(line);
        switch (column) {
        case 1 : currentEntry.setName(line); break;
        case 2 : currentEntry.setCost(ManaCostFactory.create(line)); break;
//        <table cellpadding="3" cellspacing="1"><tr bgcolor="#E8BE6C"><td><a href="http://www.wizards.com/default.asp?x=magic/cardlist/dissension&amp;tablesort=1"><b>Name</b></a></td>
//        <td><a href="http://www.wizards.com/default.asp?x=magic/cardlist/dissension&amp;tablesort=2"><b>Cost</b></a></td>
//        <td><a href="http://www.wizards.com/default.asp?x=magic/cardlist/dissension&amp;tablesort=3"><b>Type</b></a></td>
//        <td><a href="http://www.wizards.com/default.asp?x=magic/cardlist/dissension&amp;tablesort=4"><b>P/T</b></a></td>
//        <td><a href="http://www.wizards.com/default.asp?x=magic/cardlist/dissension&amp;tablesort=5"><b>Rules Text</b></a></td>
//        <td><a href="http://www.wizards.com/default.asp?x=magic/cardlist/dissension&amp;tablesort=6"><b>Flavor Text</b></a></td>
//        <td><a href="http://www.wizards.com/default.asp?x=magic/cardlist/dissension&amp;tablesort=7"><b>Rarity</b></a></td>
//        <td><a href="http://www.wizards.com/default.asp?x=magic/cardlist/dissension&amp;tablesort=8"><b>Artist</b></a></td>


        }
//            if (attributes[column].equals("P"))
//                currentPower = line;
        }

//        String attr = (String)attributeMap.get(key);
//        currentCard.setAttribute(attr, value);
    }
}
*/