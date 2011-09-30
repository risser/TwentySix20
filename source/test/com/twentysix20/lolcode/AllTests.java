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
package com.twentysix20.lolcode;

import junit.framework.Test;
import junit.framework.TestSuite;

import com.twentysix20.lolcode.code.HaiWorldCodeTest;
import com.twentysix20.lolcode.commands.TestCommandFactory;
import com.twentysix20.lolcode.commands.TestCompilationCanHas;
import com.twentysix20.lolcode.commands.TestCompilationHai;
import com.twentysix20.lolcode.commands.TestCompilationIHasA;
import com.twentysix20.lolcode.commands.TestCompilationInvisible;
import com.twentysix20.lolcode.commands.TestCompilationKthxbye;
import com.twentysix20.lolcode.commands.TestCompilationVisible;
import com.twentysix20.lolcode.commands.TestExecutionCanHas;
import com.twentysix20.lolcode.commands.TestExecutionHai;
import com.twentysix20.lolcode.commands.TestExecutionIHasA;
import com.twentysix20.lolcode.commands.TestExecutionInvisible;
import com.twentysix20.lolcode.commands.TestExecutionKthxbye;
import com.twentysix20.lolcode.commands.TestExecutionVisible;
import com.twentysix20.lolcode.expressions.TestExpressionBuilderBool;
import com.twentysix20.lolcode.expressions.TestExpressionBuilderMultiples;
import com.twentysix20.lolcode.expressions.TestExpressionBuilderNums;
import com.twentysix20.lolcode.expressions.TestExpressionBuilderStrings;

public class AllTests {

    public static Test suite() {
        TestSuite suite = new TestSuite("Test for com.twentysix20.lolcode.command.test");
        suite.addTestSuite(IdentifierTests.class);
        suite.addTestSuite(StateTests.class);
        suite.addTestSuite(UnivarTests.class);
        suite.addTestSuite(CompilerTestCase.class);
        suite.addTestSuite(ExecutorTestCase.class);
        
        suite.addTestSuite(TestExpressionBuilderStrings.class);
        suite.addTestSuite(TestExpressionBuilderNums.class);
        suite.addTestSuite(TestExpressionBuilderBool.class);
        suite.addTestSuite(TestExpressionBuilderMultiples.class);
        
        suite.addTestSuite(TestCommandFactory.class);

        suite.addTestSuite(TestCompilationHai.class);
        suite.addTestSuite(TestExecutionHai.class);
        suite.addTestSuite(TestCompilationKthxbye.class);
        suite.addTestSuite(TestExecutionKthxbye.class);
        suite.addTestSuite(TestCompilationCanHas.class);
        suite.addTestSuite(TestExecutionCanHas.class);
        suite.addTestSuite(TestCompilationVisible.class);
        suite.addTestSuite(TestExecutionVisible.class);
        suite.addTestSuite(TestCompilationInvisible.class);
        suite.addTestSuite(TestExecutionInvisible.class);
        suite.addTestSuite(TestCompilationIHasA.class);
        suite.addTestSuite(TestExecutionIHasA.class);
        
        suite.addTestSuite(HaiWorldCodeTest.class);
        return suite;
    }

}
