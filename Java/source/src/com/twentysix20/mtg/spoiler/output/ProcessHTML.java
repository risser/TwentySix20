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
* Created on Jul 13, 2006
*/
package com.twentysix20.mtg.spoiler.output;

import java.util.List;

import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.spoiler.reader.FileHtmlSortableSpoilerReader;

public class ProcessHTML {

    private static final String SOURCE_FILE = "article.aspx.htm";
    private static final String RESULT_FILE_ORACLE = "mb-oracle.txt";
    private static final String RESULT_FILE_ORIGINAL = "mb-original.txt";
    // path must end in slash
    private static final String PATH = "C:/temp/besieged/";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        FileHtmlSortableSpoilerReader reader = new FileHtmlSortableSpoilerReader(PATH+SOURCE_FILE);
        List<Card> list = reader.getEntryList();
        AbstractCardWriter oracleWriter = new OracleWriter(list);
        AbstractCardWriter originalWriter = new OriginalCardWriter(list);
        oracleWriter.generateFile(PATH+RESULT_FILE_ORACLE);
        originalWriter.generateFile(PATH+RESULT_FILE_ORIGINAL);
    }

}
