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

import com.twentysix20.lolcode.Identifier;
import com.twentysix20.testutil.TestCase2620;

public class IdentifierTests extends TestCase2620 {
    public void testGood() {
        assertTrue(Identifier.isValidIdentifier("a"));
        assertTrue(Identifier.isValidIdentifier("aa"));
        assertTrue(Identifier.isValidIdentifier("Aa"));
        assertTrue(Identifier.isValidIdentifier("aZ"));
        assertTrue(Identifier.isValidIdentifier("a1Z"));
        assertTrue(Identifier.isValidIdentifier("A_Z"));
        assertTrue(Identifier.isValidIdentifier("FISH"));
        assertTrue(Identifier.isValidIdentifier("F1SH0rZ"));
        assertTrue(Identifier.isValidIdentifier("_F1SH0rZ"));
        assertTrue(Identifier.isValidIdentifier("K1000000"));
        assertTrue(Identifier.isValidIdentifier("___EKE___"));
    }
    public void testBad() {
        assertFalse(Identifier.isValidIdentifier("12"));
        assertFalse(Identifier.isValidIdentifier("ABE-LINCOLN"));
        assertFalse(Identifier.isValidIdentifier(" ABE_LINCOLN"));
        assertFalse(Identifier.isValidIdentifier("ABE_LINCOLN "));
        assertFalse(Identifier.isValidIdentifier("ABE.LINCOLN"));
        assertFalse(Identifier.isValidIdentifier("ABE LINCOLN"));
        assertFalse(Identifier.isValidIdentifier(""));
        assertFalse(Identifier.isValidIdentifier("1SH0rZ"));
    }
}
