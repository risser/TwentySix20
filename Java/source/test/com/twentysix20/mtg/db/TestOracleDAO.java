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
* Created on Oct 7, 2006
*/
package com.twentysix20.mtg.db;

import com.twentysix20.mtg.data.card.*;
import com.twentysix20.testutil.TestCase2620;

public class TestOracleDAO extends TestCase2620 {
    public void testStoreOracle() throws Exception {
        CardTemplate template = new CardTemplate();
        template.setName("Test Card");
        template.setCost("3");
//        template.setPT("1/1");
        template.setType("Artifact");
        template.addRule("Example stuff.");
        Card card = CardFactory.create(template);

//        OracleDAO.save(card);
    }
}
