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
* Created on May 30, 2007
*/
package com.twentysix20.lolcode.command;

import com.twentysix20.lolcode.CompilerState;
import com.twentysix20.lolcode.Constants;
import com.twentysix20.lolcode.ExecutionState;
import com.twentysix20.lolcode.Identifier;
import com.twentysix20.lolcode.Univar;
import com.twentysix20.lolcode.exception.BadIdentifierException;
import com.twentysix20.lolcode.exception.IdentifierAlreadyDeclaredException;
import com.twentysix20.lolcode.exception.ImproperCommandConstructionException;
import com.twentysix20.lolcode.exception.MissingIdentifierException;
import com.twentysix20.lolcode.expression.Expression;
import com.twentysix20.lolcode.expression.ExpressionBuilder;
import com.twentysix20.lolcode.expression.ExpressionBuilderFactory;
import com.twentysix20.util.StringUtil;

public class IHasACommand extends BaseCommand {
    private Expression expression;
    private String varName;
    
    public String token() {
        return Constants.COMMAND_I_HAS_A;
    }

    public void execute(ExecutionState state) {
        super.execute(state);
        state.addVar(varName, (expression == null ? new Univar() : expression.evaluate()));
    }

    public void compile(CompilerState state) {
        if (isCompiled()) return;
        super.compile(state);

        if (StringUtil.isEmpty(line))
            throw new MissingIdentifierException(this);

        String[] ss = line.split("\\s+",3);
        if (Identifier.isValidIdentifier(ss[0])) {
            varName = ss[0];
            if (Identifier.isKeyword(varName))
                throw new BadIdentifierException(this, varName);
            if (state.hasVar(varName))
                throw new IdentifierAlreadyDeclaredException(this, varName);
            if (ss.length > 1) {
                if (!Constants.TOKEN_ITZ.equals(ss[1]))
                    throw new ImproperCommandConstructionException(this, "Missing ITZ after identifier.");
                if (ss.length == 2)
                    throw new ImproperCommandConstructionException(this, "Missing expression after ITZ.");

                ExpressionBuilder builder = ExpressionBuilderFactory.createExpression(ss[2]);
                expression = builder.getNextExpression();
            }
            state.addVar(varName);
        } else {
            throw new BadIdentifierException(this, ss[0]);
        }
    }

    public Expression getExpression() {
        return expression;
    }
}
