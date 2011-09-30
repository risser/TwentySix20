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
* Created on Sep 22, 2006
*/
package com.twentysix20.mtg.spoiler.output;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.spoiler.output.cardstring.CardStringWrapper;
import com.twentysix20.util.StringList;

public class OriginalCardWriter extends AbstractCardWriter {
    public final StringList HEADERS = new StringList(new String[]{
            "Name", "version", "Cost", "Super", "Type", "Sub", "Enchantees", "P", "T",
            "Rules Text", "Flavor", "Set", "Rarity", "Artist", "Number"
    });

    public OriginalCardWriter(List<Card> list) throws IOException {
        super(list);
    }

    protected void writeHeader(BufferedWriter outFile) throws IOException {
        outFile.write(HEADERS.toString("\t")+"\n");
    }

    protected void writeCard(BufferedWriter outFile, CardStringWrapper wrapper) throws IOException {
        StringList list = new StringList();
        list.add(wrapper.name());
        list.add("");
        list.add(wrapper.cost());
        list.add(wrapper.supertype());
        list.add(wrapper.type());
        list.add(wrapper.subtype());
        list.add("");
        list.add(wrapper.power());
        list.add(wrapper.toughness());
        list.add(wrapper.ruleText("; ").replace('\n', ' '));
        list.add(wrapper.flavorText().replace('\n', ' '));
        list.add("");
        list.add(wrapper.rarity().substring(0,1).toUpperCase());
        list.add(wrapper.artist());
        list.add(wrapper.collectorNumber());
        outFile.write(list.toString("\t")+"\n");
    }

}
