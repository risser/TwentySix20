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
import com.twentysix20.lolcode.exception.ExpressionBuilderException;
import com.twentysix20.lolcode.exception.ExpressionCompilationException;
import com.twentysix20.lolcode.expression.ExpressionBuilder;
import com.twentysix20.lolcode.expression.ExpressionBuilderFactory;

public class VisibleCommand extends BaseCommand {
    private ExpressionBuilder builder;
    private boolean hasLineBreak;
    private boolean writeError;
    
    public boolean isWriteError() {
        return writeError;
    }

    public void setWriteError(boolean writeError) {
        this.writeError = writeError;
    }

    public String token() {
        return Constants.COMMAND_VISIBLE;
    }

    public void execute(ExecutionState state) {
        super.execute(state);
        StringBuffer buf = new StringBuffer();
        while (builder.hasMore()) {
            buf.append(builder.getNextExpression().evaluate().strVal());
        }
        String val = buf.toString();
        if (state.getErrorCount() == 0)
            if (!writeError)
                state.writeStdOut(val, hasLineBreak);
            else
                state.writeStdErr(val, hasLineBreak);
    }

    public void compile(CompilerState state) {
        if (isCompiled()) return;
        super.compile(state);

        hasLineBreak = !line.endsWith("!");
        if (line.endsWith("!")) {
            line = line.substring(0,line.length()-1).trim();
        }
        try {
            builder = ExpressionBuilderFactory.createExpression(line); 
        } catch (ExpressionBuilderException e) {
            throw new ExpressionCompilationException(this, e);
        }
    }

    public boolean hasLineBreak() {
        return hasLineBreak;
    }
}
