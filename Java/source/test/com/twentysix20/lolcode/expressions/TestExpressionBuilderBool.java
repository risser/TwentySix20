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

import com.twentysix20.lolcode.Univar;
import com.twentysix20.lolcode.expression.Expression;
import com.twentysix20.lolcode.expression.ExpressionBuilder;
import com.twentysix20.lolcode.expression.ValueExpression;
import com.twentysix20.testutil.TestCase2620;

public class TestExpressionBuilderBool extends TestCase2620 {
    public void testTrue() {
        ExpressionBuilder builder = new ExpressionBuilder(Univar.TRUE);
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertTrue(e.evaluate().boolVal());
        assertEquals(Univar.TRUE, e.evaluate().strVal());
    }
    public void testFalse() {
        ExpressionBuilder builder = new ExpressionBuilder(Univar.FALSE);
        Expression e = builder.getNextExpression();
        assertInstanceOf(e, ValueExpression.class);
        assertFalse(e.evaluate().boolVal());
        assertEquals(Univar.FALSE, e.evaluate().strVal());
    }
}
