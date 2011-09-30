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
* Created on Jun 21, 2006
*/
package com.twentysix20.mtg.data;

import com.twentysix20.testutil.TestCase2620;

public class TestCardEntry extends TestCase2620 {
//    public void testSettingTwice() throws Exception {
//        CardEntry entry = new BaseEntry();
//        entry.setName("Absorb");
//        entry.setCost(ManaCostFactory.create("1UU"));
//        entry.setType(new Type("Instant"));
//        entry.setPT(new PowerToughness("1","1"));
//        entry.setArtist("Bob the Artist");
//        entry.setRarity(Rarity.COMMON);
//        entry.setCollectorNumber("7");
//        entry.setFlavorText("I was an angel.");
//        entry.addRuleText("Counter target spell. You gain 3 life.");
//        try {
//            entry.setRarity(Rarity.UNCOMMON);
//            fail ("Should have thrown illegal state exception.");
//        } catch (IllegalStateException e) {
//            // good.
//        }
//        try {
//            entry.setFlavorText("I am still an angel.");
//            fail ("Should have thrown illegal state exception.");
//        } catch (IllegalStateException e) {
//            // good.
//        }
//        try {
//            entry.setName("test");
//            fail ("Should have thrown illegal state exception.");
//        } catch (IllegalStateException e) {
//            // good.
//        }
//        try {
//            entry.setCost(ManaCostFactory.create("WWW"));
//            fail ("Should have thrown illegal state exception.");
//        } catch (IllegalStateException e) {
//            // good.
//        }
//        try {
//            entry.setPT(new PowerToughness("2","2"));
//            fail ("Should have thrown illegal state exception.");
//        } catch (IllegalStateException e) {
//            // good.
//        }
//        try {
//            entry.setType(new Type("Instant"));
//            fail ("Should have thrown illegal state exception.");
//        } catch (IllegalStateException e) {
//            // good.
//        }
//        try {
//            entry.setArtist("Joe the Artist");
//            fail ("Should have thrown illegal state exception.");
//        } catch (IllegalStateException e) {
//            // good.
//        }
//        try {
//            entry.setCollectorNumber("76");
//            fail ("Should have thrown illegal state exception.");
//        } catch (IllegalStateException e) {
//            // good.
//        }
//    }
//
//    public void testGoodCollectorNumbers() {
//        CardEntry entry = new CardEntry();
//        entry.setCollectorNumber("7");
//        entry = new CardEntry();
//        entry.setCollectorNumber("007");
//        entry = new CardEntry();
//        entry.setCollectorNumber("  7");
//        entry = new CardEntry();
//        entry.setCollectorNumber("70");
//        entry = new CardEntry();
//        entry.setCollectorNumber("070");
//        entry = new CardEntry();
//        entry.setCollectorNumber(" 70");
//        entry = new CardEntry();
//        entry.setCollectorNumber("170");
//        entry = new CardEntry();
//        entry.setCollectorNumber("170a");
//        entry = new CardEntry();
//        entry.setCollectorNumber("170B");
//    }
//    
//    public void testBadCollectorNumbers() {
//        CardEntry entry = new CardEntry();
//        try {
//            entry.setCollectorNumber("");
//            fail("Should have thrown Illegal State Exception.");
//        }
//        catch (IllegalStateException e) {
//            // good
//        }
//        try {
//            entry.setCollectorNumber("   ");
//            fail("Should have thrown Illegal State Exception.");
//        }
//        catch (IllegalStateException e) {
//            // good
//        }
//        try {
//            entry.setCollectorNumber("000");
//            fail("Should have thrown Illegal State Exception.");
//        }
//        catch (IllegalStateException e) {
//            // good
//        }
//        try {
//            entry.setCollectorNumber("1000");
//            fail("Should have thrown Illegal State Exception.");
//        }
//        catch (IllegalStateException e) {
//            // good
//        }
//        try {
//            entry.setCollectorNumber("170c");
//            fail("Should have thrown Illegal State Exception.");
//        }
//        catch (IllegalStateException e) {
//            // good
//        }
//        try {
//            entry.setCollectorNumber("twelve");
//            fail("Should have thrown Illegal State Exception.");
//        }
//        catch (IllegalStateException e) {
//            // good
//        }
//        try {
//            entry.setCollectorNumber("a170");
//            fail("Should have thrown Illegal State Exception.");
//        }
//        catch (IllegalStateException e) {
//            // good
//        }
//    }
}
