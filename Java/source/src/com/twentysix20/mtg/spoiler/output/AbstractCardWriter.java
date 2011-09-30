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
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.spoiler.output.cardstring.CardStringWrapper;
import com.twentysix20.mtg.spoiler.output.cardstring.CardStringWrapperFactory;
import com.twentysix20.util.StringUtil;

public abstract class AbstractCardWriter {
    private Set<String> alreadyWritten = new HashSet<String>();
    private List<Card> cardList;

    public AbstractCardWriter(List<Card> list) throws IOException {
        if (list == null) throw new IllegalArgumentException("Can't pass an empty card list.");
        cardList = list;
    }

    public void generateFile(String fileName) throws IOException {
        alreadyWritten = new HashSet<String>();
        if (StringUtil.isEmpty(fileName)) throw new IllegalArgumentException("Can't generate a file with no name.");
        BufferedWriter outFile = new BufferedWriter(new FileWriter(fileName));
        Iterator<Card> cards = cardList.iterator();

        writeHeader(outFile);
        while (cards.hasNext()) {
            Card card = (Card)cards.next();
            CardStringWrapper wrapper = CardStringWrapperFactory.wrapCard(card);
System.out.println(wrapper.name());
            if (!alreadyWritten.contains(wrapper.name())) {
                writeCard(outFile, wrapper);
                alreadyWritten.add(wrapper.name());
            }
        }
        outFile.close();
    }

    abstract protected void writeHeader(BufferedWriter outFile) throws IOException;
    abstract protected void writeCard(BufferedWriter outFile, CardStringWrapper wrapper) throws IOException;
}
