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
* Created on Jul 9, 2007
*/
package com.twentysix20.lolcode;

import java.util.Iterator;

import com.twentysix20.lolcode.command.Command;

public class Executor {
    private ExecutionState state;

    public Executor(ExecutionState state) {
        this.state = state;
    }

    public void execute(Program program) {
        Iterator itrCommands = program.getCommands().iterator();
        while (itrCommands.hasNext()) {
            Command cmd = (Command)itrCommands.next();
            cmd.execute(state);
        }
    }

    public ExecutionState getState() {
        return state;
    }
}
