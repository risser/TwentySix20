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
import com.twentysix20.mtg.spoiler.reader.FileOracleReader;

public class ProcessOracle {

//    private static final String SOURCE_FILE = "SHD-Shadowmoor/poop.txt";
    private static final String SOURCE_FILE = "oracle-2008-10.txt";
    private static final String RESULT_FILE = "oracle-2008-10-tabbed.txt";
    // path must end in slash
    private static final String PATH = "C:/Risser/Synch/MTG/spoilers/";

    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
// new sets
// vintage
// banned/restricted
// UN-sets
        try {
        FileOracleReader reader = new FileOracleReader(PATH+SOURCE_FILE);
        List<Card> list = reader.getEntryList();
        new OracleWriter(list).generateFile(PATH+RESULT_FILE);
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
        
//        Iterator itr = list.iterator();
//        while (itr.hasNext()) {
//            CardStringWrapper wrapper = CardStringWrapperFactory.wrapCard((Card)itr.next());
//            System.out.println(wrapper.name());
//        }
    }

}
