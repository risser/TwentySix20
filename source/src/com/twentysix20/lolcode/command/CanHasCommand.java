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

import com.twentysix20.lolcode.Constants;
import com.twentysix20.lolcode.CompilerState;
import com.twentysix20.lolcode.ExecutionState;
import com.twentysix20.lolcode.Identifier;
import com.twentysix20.lolcode.exception.BadIdentifierException;

public class CanHasCommand extends BaseCommand {

    private String importName;
    private boolean hasQuestionMark;
    
    public String token() {
        return Constants.COMMAND_CAN_HAS;
    }

    public void execute(ExecutionState state) {
        super.execute(state);
        state.addImport(importName);
    }

    public void compile(CompilerState state) {
        if (isCompiled()) return;
        super.compile(state);

        hasQuestionMark = line.endsWith("?");
        importName = (hasQuestionMark ? line.substring(0, line.length()-1) : line);
        if (!Identifier.isValidIdentifier(importName))
            throw new BadIdentifierException(this, importName);
        state.addImport(importName);
    }
}