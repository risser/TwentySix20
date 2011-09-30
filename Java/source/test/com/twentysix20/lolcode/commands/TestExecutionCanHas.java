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
package com.twentysix20.lolcode.commands;

import com.twentysix20.lolcode.CompilerState;
import com.twentysix20.lolcode.ExecutionState;
import com.twentysix20.lolcode.MockCompilerState;
import com.twentysix20.lolcode.MockExecutionState;
import com.twentysix20.lolcode.SymbolTable;
import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;
import com.twentysix20.lolcode.exception.BadIdentifierException;

public class TestExecutionCanHas extends TstCaseBaseCommand {
    private Command cmd;
    
    public void setUp() {
        CompilerState cs = new MockCompilerState();
        cmd = CommandFactory.instance().getCommand("CAN HAS STDIO");
        cmd.compile(cs);
    }

    public void testBad() {
        assertNoCompile("CAN HAS STDIO");
        assertNotReady(cmd);
    }

    public void testCanHas() {
        ExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
    }
}
