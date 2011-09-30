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
* Created on Jul 11, 2006
*/
package com.twentysix20.mtg.data.card;


public class NewSplitCard extends SplitCard {

    private static final String LINE_BREAK = "(\n|<br/?>)";

    public NewSplitCard(CardTemplate template) {
        super(template);
    }

    protected void setSides(CardTemplate template) {
        String[] texts = template.getRules().split(LINE_BREAK+"?//"+LINE_BREAK+"?");
        String[] names = template.getName().split("\\s?//\\s?");
        oracleInfo[LEFT] = new OracleInformation(names[0], template.getCost(), 
                template.getType(), template.getPT(), texts[LEFT]);

        String[] rightText = texts[RIGHT].split(LINE_BREAK);

        String name = rightText[0];
        String cost = rightText[1];
        String type = rightText[2];
        String rules = reconcat(3, rightText);
        oracleInfo[RIGHT] = new OracleInformation(name, cost, type, "", rules);
    }

    private String reconcat(int start, String[] fliptext) {
        StringBuffer buf = new StringBuffer();
        for (int i = start; i < fliptext.length; i++)
            buf.append(fliptext[i]).append("\n"); // yes, this leaves a trailing \n, but it should be ignored
        return buf.toString();
    }
}
