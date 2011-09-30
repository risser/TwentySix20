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
package com.twentysix20.lolcode.command;

import com.twentysix20.lolcode.CompilerState;
import com.twentysix20.lolcode.ExecutionState;
import com.twentysix20.lolcode.exception.UncompiledCommandException;


public abstract class BaseCommand implements Command {
    protected int lineNumber;
    protected boolean compiled;
    protected String line;
    
    public String getLine() {
        return line;
    }
    public void setLine(String line) {
        this.line = line;
    }

    public boolean isCompiled() {
        return compiled;
    }
    public void setCompiled() {
        this.compiled = true;
    }

    public int getLineNumber() {
        return lineNumber;
    }
    public void setLineNumber(int i) {
        lineNumber = i;
    }

    public void compile(CompilerState state) {
        setCompiled();
    }

    public void execute(ExecutionState state) {
        if (!isCompiled())
            throw new UncompiledCommandException(this, state);
        if (!state.isReady())
            state.addError("Program not ready.");
    }
}
