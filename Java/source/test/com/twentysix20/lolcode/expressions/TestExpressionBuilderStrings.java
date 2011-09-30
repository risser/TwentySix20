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
* Created on Jun 2, 2007
*/
package com.twentysix20.lolcode.expressions;

import com.twentysix20.lolcode.exception.ExpressionBuilderException;
import com.twentysix20.lolcode.expression.Expression;
import com.twentysix20.lolcode.expression.ExpressionBuilder;
import com.twentysix20.lolcode.expression.ValueExpression;
import com.twentysix20.testutil.TestCase2620;

public class TestExpressionBuilderStrings extends TestCase2620 {

    public void testNumber() {
        ExpressionBuilder builder = new ExpressionBuilder("2");
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("2", e.evaluate().strVal());
    }

    public void testBigNumber() {
        ExpressionBuilder builder = new ExpressionBuilder("234567890");
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("234567890", e.evaluate().strVal());
    }

    public void testNegNumber() {
        ExpressionBuilder builder = new ExpressionBuilder("-2");
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("-2", e.evaluate().strVal());
    }

    public void testRealNumber() {
        ExpressionBuilder builder = new ExpressionBuilder("2.02");
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("2.02", e.evaluate().strVal());
    }

    public void testBigRealNumber() {
        ExpressionBuilder builder = new ExpressionBuilder("2345678.02345678");
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("2345678.02345678", e.evaluate().strVal());
    }

    public void testNegRealNumber() {
        ExpressionBuilder builder = new ExpressionBuilder("-2.02");
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("-2.02", e.evaluate().strVal());
    }

    public void testBadRealNoStart() {
        try {
            ExpressionBuilder builder = new ExpressionBuilder(".02");
            Expression e = builder.getNextExpression();
            fail("Should have thrown exception for bad structure.");
        } catch (ExpressionBuilderException e) {
            // good
        }
    }

    public void testBadRealNoEnd() {
        try {
            ExpressionBuilder builder = new ExpressionBuilder("20.");
            Expression e = builder.getNextExpression();
            fail("Should have thrown exception for bad structure.");
        } catch (ExpressionBuilderException e) {
            // good
        }
    }

    public void testBadRealTwoDecimals() {
        try {
            ExpressionBuilder builder = new ExpressionBuilder("2.02.02");
            Expression e = builder.getNextExpression();
            fail("Should have thrown exception for bad structure.");
        } catch (ExpressionBuilderException e) {
            // good
        }
    }

    public void testBadMidHyphen() {
        try {
            ExpressionBuilder builder = new ExpressionBuilder("202-02");
            Expression e = builder.getNextExpression();
            fail("Should have thrown exception for bad structure.");
        } catch (ExpressionBuilderException e) {
            // good
        }
    }
}
