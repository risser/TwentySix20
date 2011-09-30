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
import com.twentysix20.lolcode.exception.ImproperBlockConstructionException;

public class KthxbyeCommand extends BaseCommand {

    public String token() {
        return Constants.COMMAND_KTHXBYE;
    }

    public void execute(ExecutionState state) {
        super.execute(state);
        if (!state.isReady())
            state.addError("Block was not opened or has already been closed.");
        else
            state.clear();
    }

    public void compile(CompilerState state) {
        super.compile(state);
//        if (!state.isReady())
//            throw new ImproperBlockConstructionException(this, "Block was not opened or has already been closed.");
        state.clear();
    }
}
