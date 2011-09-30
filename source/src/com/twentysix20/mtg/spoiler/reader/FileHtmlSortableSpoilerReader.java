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

import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.data.card.CardFactory;
import com.twentysix20.mtg.data.card.CardTemplate;
import com.twentysix20.util.StringUtil;

public class FileHtmlSortableSpoilerReader extends BaseFileReader {
    static private final String _NUMBER = "#";
    static private final String _NAME = "Name";
    static private final String _COST = "Cost";
    static private final String _PT = "P/T";
    static private final String _TYPE = "Type";
    static private final String _RULES = "Rules Text";
    static private final String _FLAVOR = "Flavor Text";
    static private final String _RARITY = "Rarity";
    static private final String _ARTIST = "Artist";
    static private final int NUMBER_OF_COLUMNS = 9;

    private boolean readingCards;
    private boolean readingHeaders;
//    private boolean isMidLine;
    private String gridValue;
//    private int freeTextColumn;
    private int cardCount;
    private int columnCursor;
    private String[] columnCaptions;

    public FileHtmlSortableSpoilerReader(String fn) throws IOException {
        super(fn);
        readingCards = false;
        readingHeaders = false;
//        freeTextColumn = 0;
        cardCount = 0;
        resetColumnCursor();
        gridValue = "";
        columnCaptions = new String[NUMBER_OF_COLUMNS];
    }

    public void handleLine(String line) {
//System.out.println("1- "+line);
        // indicates the start of the card section.  Actually the first line of each card.
        if (StringUtil.contains(line, "<table")) {
            readingHeaders = true;
            readingCards = false;
        }
        
        // turn it off at the end of the table
        if (StringUtil.contains(line, "/table")) {
            readingCards = false;
            readingHeaders = false;
        }

        // if we're not reading cards yet, move along
        if (!(readingCards || readingHeaders)) return;

        line = cleanString(line);
//System.out.println("2- "+line);
        if (StringUtil.contains(line, "<tr")) {
            if (template != null) throw new IllegalStateException("Got a second <tr> before the closing </tr>.");
        }

        boolean doneWithSection = (StringUtil.contains(line, "</tr>"));
        boolean hasOpeningTD = (StringUtil.contains(line, "<td"));
        boolean hasClosingTD = (StringUtil.contains(line, "</td>"));
//System.out.println("dWS="+doneWithSection);                

        line = line.replaceAll("</?t[dr][^<]*>","");
        line = line.replaceAll("&nbsp;"," ");
        line = line.trim();

        if (hasOpeningTD)
            gridValue = line;
        else
            gridValue += " "+line;
//System.out.println("3- "+gridValue);

        if (hasClosingTD) {
//System.out.println("4- "+gridValue);
            columnCursor++;
System.out.print("::"+cardCount+"::"+columnCursor+"::"+gridValue+"::");
            if (columnCursor >= NUMBER_OF_COLUMNS)
                throw new IllegalStateException("columnCursor is ["+columnCursor+"] and number of columns is ["+NUMBER_OF_COLUMNS+"].");

            if (readingHeaders) {
                System.out.println();                
//                String caption = StringUtil.grab(gridValue, "<b>", "</b>").trim();
                String caption = cleanString(gridValue);
                columnCaptions[columnCursor] = caption;
//System.out.println("HEADER");                
            } else if (readingCards) {
                line = cleanString(gridValue);
                gridValue = "";
                String caption = columnCaptions[columnCursor];
System.out.println(caption);

                if (template == null) {
                    resetReader();
                    cardCount++;
                }
                
                if (_NAME.equals(caption))
                    getCurrentTemplate().setName(line);
                else if (_COST.equals(caption))
                    getCurrentTemplate().setCost(line.replaceAll("<br>",""));
                else if (_RARITY.equals(caption))
                    getCurrentTemplate().setRarity(line);
                else if (_TYPE.equals(caption))
                    getCurrentTemplate().setType(line);
                else if (_PT.equals(caption))
                    getCurrentTemplate().setPT(line);
                else if (_NUMBER.equals(caption))
                    getCurrentTemplate().setNumber(line);
                else if (_RULES.equals(caption)) {
                    if (!line.matches("\\[[WUBRG]\\]")) { // ignore the basic land [X] abilities & empty rules text
                        getCurrentTemplate().setRules(line);
                    }
                } else if (_FLAVOR.equals(caption)) {
                    line = line.replaceAll("<br/?>","\n");
                    getCurrentTemplate().setFlavor(line);
                } else if (_ARTIST.equals(caption))
                    getCurrentTemplate().setArtist(line);
                else
                    throw new IllegalStateException("Whoa!  Too many free text columns! ("+template.getName()+":" + line + ")");
            } else {
                throw new IllegalStateException("Not reading headers or cards; should never have gotten here!");
            }
        }

        if (doneWithSection) {
            resetColumnCursor();
            if (readingHeaders) {
                readingHeaders = false;
                readingCards = true;
            } else if (readingCards) {
                addCurrentEntryToList();
            }
        }
    }

    private void resetColumnCursor() {
        columnCursor = -1;
    }

    private void resetReader() {
        template = new CardTemplate();
//        freeTextColumn = 0;
        gridValue = "";
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
        for (int i = 20; i >= 0; i--)
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