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
* Created on Jun 6, 2007
*/
package com.twentysix20.oekaki;

import junit.framework.Test;
import junit.framework.TestSuite;

public class AllOekakiTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.twentysix20.oekaki.test");

        suite.addTestSuite(Board_Setup_TestCase.class);
        suite.addTestSuite(LineTestCase.class);
        suite.addTestSuite(TrayMatchTestCase.class);
        suite.addTestSuite(TrayMergeTestCase.class);
        suite.addTestSuite(TraySet_Collate_TestCase.class);
        suite.addTestSuite(TraySet_LineConstructor_TestCase.class);
        suite.addTestSuite(TrayTestCase.class);
        suite.addTestSuite(PuzzleTestCase.class);

        return suite;
    }
}