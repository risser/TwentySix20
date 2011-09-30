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
package com.twentysix20.lolcode.commands;

import com.twentysix20.lolcode.CompilerState;
import com.twentysix20.lolcode.ExecutionState;
import com.twentysix20.lolcode.MockExecutionState;
import com.twentysix20.lolcode.TstCaseLolcode;
import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;
import com.twentysix20.lolcode.exception.UncompiledCommandException;

public class TstCaseBaseCommand extends TstCaseLolcode {
    public static void assertNoProblems(ExecutionState state) {
        assertTrue(state.isReady());
        if (state.hasErrors())
            fail("State has Errors: "+state.getErrors());
    }

    public static void assertHasVar(CompilerState state, String name) {
        assertTrue("Var '"+name+"' does not exist.", state.hasVar(name));
    }

    public void assertNoCompile(String line) {
        try {
            CommandFactory.instance().getCommand(line).execute(new MockExecutionState());
            fail("Should have thrown exception.");
        } catch (UncompiledCommandException e) {
            // yes 
        }
    }

    public void assertNotReady(Command cmd) {
        ExecutionState state = new MockExecutionState("");
        cmd.execute(state);
        assertFalse(state.isReady());
        assertEquals(1, state.getErrorCount());
    }

}
