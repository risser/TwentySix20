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
* Created on Mar 24, 2006
*/
package com.twentysix20.mtg.data;

import com.twentysix20.mtg.data.mana.Costless;
import com.twentysix20.mtg.data.mana.ManaCost;
import com.twentysix20.mtg.data.mana.ManaCostFactory;
import com.twentysix20.testutil.TestCase2620;

public class TestManaCost extends TestCase2620 {
    public void testGoodCosts() {
        ManaCost c;
        c = ManaCostFactory.create("W");
        c = ManaCostFactory.create("   ");
        c = ManaCostFactory.create("{ }");
        c = ManaCostFactory.create("");
        c = ManaCostFactory.create("WB");
        c = ManaCostFactory.create("WGRBU");
        c = ManaCostFactory.create("1");
        c = ManaCostFactory.create("12");
        c = ManaCostFactory.create("12UUU");
        c = ManaCostFactory.create("1UUR");
        c = ManaCostFactory.create("X");
        c = ManaCostFactory.create("XY");
        c = ManaCostFactory.create("X2RR");
        c = ManaCostFactory.create("XRW");
        c = ManaCostFactory.create("XXR");
        c = ManaCostFactory.create("XYZRR");
        c = ManaCostFactory.create("1rubwg");
        c = ManaCostFactory.create("2W ");
        c = ManaCostFactory.create(" 2W");
        c = ManaCostFactory.create("{1}{U}{U}");
        c = ManaCostFactory.create(" {1} ");
        c = ManaCostFactory.create("{0}");
        c = ManaCostFactory.create("W (half)");
        c = ManaCostFactory.create("½w");
        c = ManaCostFactory.create("(w/u)(w/u)");
        c = ManaCostFactory.create("(g/u)(g/u)");
        c = ManaCostFactory.create("(u/r)(u/r)");
        c = ManaCostFactory.create("(b/r)(b/r)");
        c = ManaCostFactory.create("(r/W)");
        c = ManaCostFactory.create("(R/G)");
        c = ManaCostFactory.create("1(U/b)");
        c = ManaCostFactory.create("2(G/W)(g/w)");
        c = ManaCostFactory.create("3(b/g)(b/g)(b/g)");
        c = ManaCostFactory.create("4(w/b)(w/b)(w/b)");
        c = ManaCostFactory.create("1,000,000");
    }
    public void testBadCosts() {
        tryBadCost("SW");
        tryBadCost("2XR");
        tryBadCost("YXR");
        tryBadCost("R (half)");
        tryBadCost("w half");
        tryBadCost("W/2");
    }
    public void testToString() {
        assertEquals("W",ManaCostFactory.create("W").toString());
        assertEquals("",ManaCostFactory.create("{}").toString());
        assertEquals("",ManaCostFactory.create("").toString());
        assertEquals("",ManaCostFactory.create("   ").toString());
        assertEquals("",ManaCostFactory.create("{ }").toString());
        assertEquals("WW",ManaCostFactory.create("WW").toString());
        assertEquals("WB",ManaCostFactory.create("WB").toString());
        assertEquals("WUBRG",ManaCostFactory.create("WGRBU").toString());
        assertEquals("1",ManaCostFactory.create("1").toString());
        assertEquals("12",ManaCostFactory.create("12").toString());
        assertEquals("0",ManaCostFactory.create("0").toString());
        assertEquals("0",ManaCostFactory.create("{0}").toString());
        assertEquals("12UUU",ManaCostFactory.create("12UUU").toString());
        assertEquals("1UUR",ManaCostFactory.create("1UUR").toString());
        assertEquals("X",ManaCostFactory.create("X").toString());
        assertEquals("XY",ManaCostFactory.create("XY").toString());
        assertEquals("X2RR",ManaCostFactory.create("X2RR").toString());
        assertEquals("XRWW",ManaCostFactory.create("XWRW").toString());
        assertEquals("XXR",ManaCostFactory.create("XXR").toString());
        assertEquals("XYZRR",ManaCostFactory.create("XYZRR").toString());
        assertEquals("1WUBRG",ManaCostFactory.create("1rubwg").toString());
        assertEquals("1UU",ManaCostFactory.create("{1}{U}{U}").toString());
        assertEquals("1",ManaCostFactory.create(" {1} ").toString());
        assertEquals("½W",ManaCostFactory.create("W (half)").toString());
        assertEquals("WUBR",ManaCostFactory.create("rubw").toString());
        assertEquals("UBRG",ManaCostFactory.create("rubg").toString());
        assertEquals("BRGW",ManaCostFactory.create("grbw").toString());
        assertEquals("RGWU",ManaCostFactory.create("urgw").toString());
        assertEquals("GWUB",ManaCostFactory.create("ubgw").toString());
        assertEquals("WU",ManaCostFactory.create("uw").toString());
        assertEquals("WB",ManaCostFactory.create("BW").toString());
        assertEquals("2UB",ManaCostFactory.create("2Ub").toString());
        assertEquals("UUR",ManaCostFactory.create("ruu").toString());
        assertEquals("BR",ManaCostFactory.create("rb").toString());
        assertEquals("BBGG",ManaCostFactory.create("BGGB").toString());
        assertEquals("RRGG",ManaCostFactory.create("grgr").toString());
        assertEquals("RW",ManaCostFactory.create("wr").toString());
        assertEquals("1GW",ManaCostFactory.create("1wg").toString());
        assertEquals("GUU",ManaCostFactory.create("ugu").toString());
        assertEquals("(U/B)(U/B)",ManaCostFactory.create("(B/u)(u/b)").toString());
        assertEquals("X2(U/R)(U/R)",ManaCostFactory.create("x2(R/u)(U/r)").toString());
        assertEquals("(W/B)(W/B)",ManaCostFactory.create("(b/W)(w/b)").toString());
        assertEquals("2(W/U)(W/U)(W/U)",ManaCostFactory.create("2(w/u)(u/w)(w/u)").toString());
        assertEquals("(R/W)",ManaCostFactory.create("(w/r)").toString());
        assertEquals("1(R/G)(R/G)",ManaCostFactory.create("1(g/r) (r/G)").toString());
        assertEquals("(B/R)(B/R)",ManaCostFactory.create("(b/r)(r/b)").toString());
        assertEquals("2(B/G)(B/G)",ManaCostFactory.create("2(b/g)(g/b)").toString());
        assertEquals("(G/W)(G/W)",ManaCostFactory.create("(w/g)(g/w)").toString());
        assertEquals("2(G/U)(G/U)",ManaCostFactory.create("2(g/u)(u/g)").toString());
        assertEquals("1000000",ManaCostFactory.create("1,000,000").toString());
    }
    public void testEquals() {
        assertEquals(ManaCostFactory.create("W"),ManaCostFactory.create("W"));
        assertEquals(ManaCost.costless(),ManaCostFactory.create("{}"));
        assertEquals(ManaCost.costless(),ManaCostFactory.create(""));
        assertEquals(ManaCost.costless(),ManaCostFactory.create("   "));
        assertEquals(ManaCost.costless(),ManaCostFactory.create("{ }"));
        assertEquals(ManaCostFactory.create("WW"),ManaCostFactory.create("WW"));
        assertEquals(ManaCostFactory.create("WB"),ManaCostFactory.create("WB"));
        assertEquals(ManaCostFactory.create("WUBRG"),ManaCostFactory.create("WGRBU"));
        assertEquals(ManaCostFactory.create("1"),ManaCostFactory.create("1"));
        assertEquals(ManaCostFactory.create("12"),ManaCostFactory.create("12"));
        assertEquals(ManaCostFactory.create("0"),ManaCostFactory.create("0"));
        assertEquals(ManaCostFactory.create("0"),ManaCostFactory.create("{0}"));
        assertEquals(ManaCostFactory.create("12UUU"),ManaCostFactory.create("12UUU"));
        assertEquals(ManaCostFactory.create("1UUR"),ManaCostFactory.create("1UUR"));
        assertEquals(ManaCostFactory.create("X"),ManaCostFactory.create("X"));
        assertEquals(ManaCostFactory.create("XY"),ManaCostFactory.create("XY"));
        assertEquals(ManaCostFactory.create("X2RR"),ManaCostFactory.create("X2RR"));
        assertEquals(ManaCostFactory.create("XWR"),ManaCostFactory.create("XRW"));
        assertEquals(ManaCostFactory.create("XXR"),ManaCostFactory.create("XXR"));
        assertEquals(ManaCostFactory.create("XYZRR"),ManaCostFactory.create("XYZRR"));
        assertEquals(ManaCostFactory.create("1WUBRG"),ManaCostFactory.create("1rubwg"));
        assertEquals(ManaCostFactory.create("1UU"),ManaCostFactory.create("{1}{U}{U}"));
        assertEquals(ManaCostFactory.create("1"),ManaCostFactory.create(" {1} "));
        assertFalse(ManaCostFactory.create("½W").equals(ManaCostFactory.create("W")));
        assertEquals(ManaCostFactory.create("½W"),ManaCostFactory.create("W (half)"));
        assertEquals(ManaCost.costless(),Costless.getInstance()); 
        assertFalse(ManaCost.costless().equals(ManaCostFactory.create("X1RR")));
        assertFalse(ManaCostFactory.create("X1RR").equals(ManaCost.costless()));
        assertEquals(ManaCostFactory.create("(U/R)(U/R)"),ManaCostFactory.create("(u/r)(u/r)"));
        assertEquals(ManaCostFactory.create("(U/R)(U/R)"),ManaCostFactory.create("(r/u)(r/u)"));
    }
    public void testConvertedCost() {
        assertEquals(1,ManaCostFactory.create("XXR").convertedCost());
        assertEquals(1,ManaCostFactory.create("W").convertedCost());
        assertEquals(0,ManaCostFactory.create("{}").convertedCost());
        assertEquals(0,ManaCostFactory.create("").convertedCost());
        assertEquals(0,ManaCostFactory.create("   ").convertedCost());
        assertEquals(0,ManaCostFactory.create("{ }").convertedCost());
        assertEquals(2,ManaCostFactory.create("WW").convertedCost());
        assertEquals(2,ManaCostFactory.create("WB").convertedCost());
        assertEquals(5,ManaCostFactory.create("WGRBU").convertedCost());
        assertEquals(1,ManaCostFactory.create("1").convertedCost());
        assertEquals(12,ManaCostFactory.create("12").convertedCost());
        assertEquals(0,ManaCostFactory.create("0").convertedCost());
        assertEquals(0,ManaCostFactory.create("{0}").convertedCost());
        assertEquals(15,ManaCostFactory.create("12UUU").convertedCost());
        assertEquals(4,ManaCostFactory.create("1UUR").convertedCost());
        assertEquals(0,ManaCostFactory.create("X").convertedCost());
        assertEquals(0,ManaCostFactory.create("XY").convertedCost());
        assertEquals(4,ManaCostFactory.create("X2RR").convertedCost());
        assertEquals(3,ManaCostFactory.create("XWRW").convertedCost());
        assertEquals(2,ManaCostFactory.create("XYZRR").convertedCost());
        assertEquals(6,ManaCostFactory.create("1rubwg").convertedCost());
        assertEquals(3,ManaCostFactory.create("{1}{U}{U}").convertedCost());
        assertEquals(1,ManaCostFactory.create(" {1} ").convertedCost());
        assertEquals(4,ManaCostFactory.create("rubw").convertedCost());
        assertEquals(4,ManaCostFactory.create("rubg").convertedCost());
        assertEquals(4,ManaCostFactory.create("grbw").convertedCost());
        assertEquals(4,ManaCostFactory.create("urgw").convertedCost());
        assertEquals(4,ManaCostFactory.create("ubgw").convertedCost());
        assertEquals(2,ManaCostFactory.create("uw").convertedCost());
        assertEquals(2,ManaCostFactory.create("BW").convertedCost());
        assertEquals(4,ManaCostFactory.create("2Ub").convertedCost());
        assertEquals(3,ManaCostFactory.create("ruu").convertedCost());
        assertEquals(2,ManaCostFactory.create("rb").convertedCost());
        assertEquals(4,ManaCostFactory.create("BGGB").convertedCost());
        assertEquals(4,ManaCostFactory.create("grgr").convertedCost());
        assertEquals(2,ManaCostFactory.create("wr").convertedCost());
        assertEquals(3,ManaCostFactory.create("1wg").convertedCost());
        assertEquals(3,ManaCostFactory.create("ugu").convertedCost());
        assertEquals(2,ManaCostFactory.create("(B/u)(u/b)").convertedCost());
        assertEquals(4,ManaCostFactory.create("x2(R/u)(U/r)").convertedCost());
        assertEquals(2,ManaCostFactory.create("(b/W)(w/b)").convertedCost());
        assertEquals(5,ManaCostFactory.create("2(w/u)(u/w)(w/u)").convertedCost());
        assertEquals(1,ManaCostFactory.create("(w/r)").convertedCost());
        assertEquals(3,ManaCostFactory.create("1(g/r) (r/G)").convertedCost());
        assertEquals(2,ManaCostFactory.create("(b/r)(r/b)").convertedCost());
        assertEquals(4,ManaCostFactory.create("2(b/g)(g/b)").convertedCost());
        assertEquals(2,ManaCostFactory.create("(w/g)(g/w)").convertedCost());
        assertEquals(4,ManaCostFactory.create("2(g/u)(u/g)").convertedCost());
        assertEquals(1000000,ManaCostFactory.create("1,000,000").convertedCost());
//        assertEquals(000,ManaCostFactory.create("W (half)").convertedCost());
    }

    private void tryBadCost(String cost) {
        try {
            ManaCost c = ManaCostFactory.create(cost);
            fail("Should have thrown IllegalArgumentException."); 
        } catch (IllegalArgumentException e) {
            // good 
        }
    }
}
