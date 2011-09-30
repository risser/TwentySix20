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
* Copyright (c) 2007 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on May 31, 2007
*/
package com.twentysix20.lolcode;

public class Identifier {
    public static final String IDENTIFIER = "[A-Za-z_][A-Za-z_0-9]*";
    public static final String KEYWORDS = Univar.FALSE+"|"+Univar.TRUE;

    public static boolean isValidIdentifier(String name) {
        return name.matches(IDENTIFIER);
    }

    public static boolean isKeyword(String name) {
        return name.matches(KEYWORDS);
    }

}
