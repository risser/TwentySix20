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
* Created on Jul 6, 2006
*/
package com.twentysix20.mtg.data.card;

import com.twentysix20.util.StringUtil;

public class CardFactory {

    private CardFactory() {}

    public static Card create(CardTemplate template) {
        if (StringUtil.contains(template.getRules(),"//") && !StringUtil.contains(template.getCost(),"//"))
            return new NewSplitCard(template);
        else if (StringUtil.contains(template.getName(),"//"))
            return new OldSplitCard(template);
        else if (StringUtil.contains(template.getRules(),"-----") && !StringUtil.contains(template.getName(),"Who/What"))
            return new FlipCard(template);
        else
            return new StandardCard(template);
    }
}
