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
* Created on Jul 12, 2006
*/
package com.twentysix20.mtg.spoiler.output.cardstring;

import com.twentysix20.mtg.data.card.Card;
import com.twentysix20.mtg.data.card.FlipCard;
import com.twentysix20.mtg.data.card.SplitCard;
import com.twentysix20.mtg.data.card.StandardCard;

public class CardStringWrapperFactory {

    public static CardStringWrapper wrapCard(Card card) {
        if (card instanceof SplitCard)
            return new SplitCardStringWrapper((SplitCard)card);
        else if (card instanceof FlipCard)
            return new FlipCardStringWrapper((FlipCard)card);
        else
            return new StandardCardStringWrapper((StandardCard)card);
    }
}
