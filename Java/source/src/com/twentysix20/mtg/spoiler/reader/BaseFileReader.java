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
* Created on Jun 22, 2006
*/
package com.twentysix20.mtg.spoiler.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.twentysix20.io.FileScanner;
import com.twentysix20.io.FileScannerListener;
import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.data.card.CardTemplate;
import com.twentysix20.util.StringUtil;

public abstract class BaseFileReader implements FileScannerListener, CardReader {

    protected CardTemplate template;
    protected List<Card> entryList = new ArrayList<Card>();
    protected String fileName;
    protected FileScanner filescan;

    public BaseFileReader(String fn) throws IOException {
        super();
        fileName = fn;
        filescan = new FileScanner(fileName);
        filescan.addListener(this);
    }

    protected String cleanString(String text) {
        text = text.trim();
        if (text.equals("Eaturecray - Igpay"))
            text = "Creature - Pig";
//        if (text.equals("Enchant Player"))
//            text = "Enchantment - Aura";
        if (text.equals("Interrupt"))
            text = "Instant";
        if ((text.indexOf("Summon") > -1) && (text.indexOf("Summon the") < 0))
            text = text.replaceAll("Summon ", "Creature - ");
        if (text.indexOf("[Demon]") > -1)
            text = text.replaceAll("\\[Demon\\] ", "");
        text = text.replaceAll("/\\s+/", "//").replaceAll("\\s+/\\s+", "/");
        text = text.replaceAll("â€\"","-").replaceAll("â€™","'");
        return text.replaceAll("\\s+"," ").replaceAll("’","'").replaceAll("[“”]","\"").replaceAll("—","-").trim();
    }

    protected void addCurrentEntryToList() {
        if ((template != null) && !StringUtil.isEmpty(template.getName()))
            entryList.add(createEntryFromTemplate());
        template = null;
    }

    public void scanComplete() {
        addCurrentEntryToList();
    }

    public List<Card> getEntryList() throws IOException {
        if (entryList.size() == 0)
            filescan.scanFile();
        return entryList;
    }

    protected CardTemplate getCurrentTemplate() {
        return template;
    }

    public abstract Card createEntryFromTemplate();

}
