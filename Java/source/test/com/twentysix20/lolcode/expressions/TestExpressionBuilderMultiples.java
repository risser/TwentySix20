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

import com.twentysix20.lolcode.expression.Expression;
import com.twentysix20.lolcode.expression.ExpressionBuilder;
import com.twentysix20.lolcode.expression.ValueExpression;
import com.twentysix20.testutil.TestCase2620;

public class TestExpressionBuilderMultiples extends TestCase2620 {
    public void testNumbers() {
        ExpressionBuilder builder = new ExpressionBuilder("2 44");

        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("2", e.evaluate().strVal());

        e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("44", e.evaluate().strVal());
    }

    public void testStrings() {
        ExpressionBuilder builder = new ExpressionBuilder("\"HAI\" \"WORLD\"");

        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("HAI", e.evaluate().strVal());

        e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("WORLD", e.evaluate().strVal());
    }

    public void testSeveral() {
        ExpressionBuilder builder = new ExpressionBuilder("\"HAI\" 23 \"WORLD\" 42");
        assertEquals(4, builder.count());

        assertTrue(builder.hasMore());
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("HAI", e.evaluate().strVal());

        assertTrue(builder.hasMore());
        e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("23", e.evaluate().strVal());

        assertTrue(builder.hasMore());
        e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("WORLD", e.evaluate().strVal());

        assertTrue(builder.hasMore());
        e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertEquals("42", e.evaluate().strVal());

        assertFalse(builder.hasMore());
    }
}