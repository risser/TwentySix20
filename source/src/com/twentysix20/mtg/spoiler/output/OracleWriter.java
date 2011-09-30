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
import com.twentysix20.util.StringUtil;

public class OracleWriter extends AbstractCardWriter {
    public final StringList HEADERS = new StringList(new String[]{
            "Name", "Debut", "Rarity", "Cost", "Type", "Super", "Sub", "Ench.", "P", "T",
            "Text", "X", "c", "W", "U", "B", "R", "G"
    });

    public OracleWriter(List<Card> list) throws IOException {
        super(list);
    }

    protected void writeHeader(BufferedWriter outFile) throws IOException {
        outFile.write(HEADERS.toString("\t")+"\n");
    }

    protected void writeCard(BufferedWriter outFile, CardStringWrapper wrapper) throws IOException {
        StringList list = new StringList();
        list.add(wrapper.name());
        list.add("");
        list.add(wrapper.rarity().substring(0,1));
        list.add(wrapper.cost());
        list.add(wrapper.type());
        list.add(wrapper.supertype());
        list.add(wrapper.subtype());
        list.add("");
        list.add(wrapper.power());
        list.add(wrapper.toughness());
        list.add(wrapper.ruleText("; ").replace('\n', ' '));
        list.add(countCost(wrapper, 'X'));
        list.add(generic(wrapper));
        list.add(countCost(wrapper, 'W'));
        list.add(countCost(wrapper, 'U'));
        list.add(countCost(wrapper, 'B'));
        list.add(countCost(wrapper, 'R'));
        list.add(countCost(wrapper, 'G'));
        outFile.write(list.toString("\t")+"\n");
//        System.out.println(list.toString("\t")+"\n");
    }

    private String generic(CardStringWrapper wrapper) {
//if (StringUtil.contains(wrapper.name(), "//"))
//    System.out.println("### "+wrapper.name());
        String[] ss = wrapper.cost().split("\\s*//\\s*");
        int g = 0;
        for (int i = 0; i < ss.length; i++) {
            String s = ss[i].replaceAll("[WUBRGXYZP\\(\\)/½]","");
            g = g + (StringUtil.isEmpty(s) ? 0 : Integer.parseInt(s));
        }
        return ""+g;
    }

    private String countCost(CardStringWrapper wrapper, char c) {
        String s = wrapper.cost();
        s = s.toUpperCase();
        int count1 = 0;
        for (int i = 0; i < s.length(); i++)
            if (c == s.charAt(i))
                count1++;
        return ""+count1;
    }

}
