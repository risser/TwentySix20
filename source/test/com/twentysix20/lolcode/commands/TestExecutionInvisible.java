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
import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;
import com.twentysix20.testutil.TestCase2620;

public class TestExecutionInvisible extends TstCaseBaseCommand {

    public void testBad() {
        assertNoCompile("INVISIBLE \"DOO DOO\"");

        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("INVISIBLE \"DOO DOO\"");
        cmd.compile(cs);
        assertNotReady(cmd);
    }

    public void testInvisible() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("INVISIBLE \"DOO DOO\"");
        cmd.compile(cs);
        MockExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
        assertEquals("DOO DOO\n", state.getStdErr());
        assertEquals("", state.getStdOut());
    }

    public void testInvisibleTwoLines() {
        CompilerState cs = new MockCompilerState();
        MockExecutionState es = new MockExecutionState("");

        Command cmd = CommandFactory.instance().getCommand("INVISIBLE \"GOO GOO\"");
        cmd.compile(cs);
        es.setReady(true);
        cmd.execute(es);

        cmd = CommandFactory.instance().getCommand("INVISIBLE \"GA GA\"");
        cmd.compile(cs);
        es.setReady(true);
        cmd.execute(es);

        assertTrue(es.isReady());
        assertEquals(0, es.getErrorCount());
        assertEquals("GOO GOO\nGA GA\n", es.getStdErr());
        assertEquals("", es.getStdOut());
    }

    public void testInvisibleTwoLinesNoBreak() {
        CompilerState cs = new MockCompilerState();
        MockExecutionState es = new MockExecutionState("");

        Command cmd = CommandFactory.instance().getCommand("INVISIBLE \"GOO GOO\"!");
        cmd.compile(cs);
        es.setReady(true);
        cmd.execute(es);

        cmd = CommandFactory.instance().getCommand("INVISIBLE \"GA GA\"!");
        cmd.compile(cs);
        es.setReady(true);
        cmd.execute(es);

        assertTrue(es.isReady());
        assertEquals(0, es.getErrorCount());
        assertEquals("GOO GOOGA GA", es.getStdErr());
        assertEquals("", es.getStdOut());
    }

    public void testInvisibleiMixedCase() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("INVISIBLE \"Doo doO\"");
        cmd.compile(cs);
        MockExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
        assertEquals("Doo doO\n", state.getStdErr());
        assertEquals("", state.getStdOut());
    }
}
