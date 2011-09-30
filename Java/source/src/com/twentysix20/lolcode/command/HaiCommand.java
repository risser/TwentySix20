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

public class HaiCommand extends BaseCommand {

    public String token() {
        return Constants.COMMAND_HAI;
    }

    public void execute(ExecutionState state) {
        super.execute(state);
        if (state.isReady())
            state.addError("Redundant "+token()+": Program already begun.");
        else
            state.setReady(true);
    }

    public void setLine(String string) {
        // it'll never happen
    }

    public void compile(CompilerState state) {
        super.compile(state);
//        if (state.isReady())
//            throw new ImproperBlockConstructionException(this, "Block is not closed or was already open.");
//        state.setReady(true);
    }
}