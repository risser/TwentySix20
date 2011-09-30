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

import java.math.BigDecimal;

import com.twentysix20.lolcode.CompilerState;
import com.twentysix20.lolcode.MockCompilerState;
import com.twentysix20.lolcode.MockExecutionState;
import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;

public class TestExecutionIHasA extends TstCaseBaseCommand {
    private Command cmd;
    
    public void testBad() {
        assertNoCompile("I HAS A FISH");

        CompilerState cs = new MockCompilerState();
        cmd = CommandFactory.instance().getCommand("I HAS A FISH");
        cmd.compile(cs);
        assertNotReady(cmd);
    }

    public void testIHasANoValue() {
        CompilerState cs = new MockCompilerState();
        cmd = CommandFactory.instance().getCommand("I HAS A FISH");
        cmd.compile(cs);

        MockExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
        assertTrue(state.hasVar("FISH"));
        assertTrue(state.getVar("FISH").isNoob());
        assertEquals("", state.getStdOut());
        assertEquals("", state.getStdErr());
    }

    public void testIHasAWithStringValue() {
        CompilerState cs = new MockCompilerState();
        cmd = CommandFactory.instance().getCommand("I HAS A FISH ITZ \"DOG\"");
        cmd.compile(cs);

        MockExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
        assertTrue(state.hasVar("FISH"));
        assertEquals("DOG", state.getVar("FISH").strVal());
        assertEquals("", state.getStdOut());
        assertEquals("", state.getStdErr());
    }

    public void testIHasAWithMixedCaseStringValue() {
        CompilerState cs = new MockCompilerState();
        cmd = CommandFactory.instance().getCommand("I HAS A FISH ITZ \"Dog\"");
        cmd.compile(cs);

        MockExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
        assertTrue(state.hasVar("FISH"));
        assertEquals("Dog", state.getVar("FISH").strVal());
        assertEquals("", state.getStdOut());
        assertEquals("", state.getStdErr());
    }

    public void testIHasAWithIntValue() {
        CompilerState cs = new MockCompilerState();
        cmd = CommandFactory.instance().getCommand("I HAS A FISH ITZ 50");
        cmd.compile(cs);

        MockExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
        assertTrue(state.hasVar("FISH"));
        assertEquals(new BigDecimal("50"), state.getVar("FISH").numVal());
        assertEquals("", state.getStdOut());
        assertEquals("", state.getStdErr());
    }

    public void testIHasAWithBoolValue() {
        CompilerState cs = new MockCompilerState();
        cmd = CommandFactory.instance().getCommand("I HAS A FISH ITZ WIN");
        cmd.compile(cs);

        MockExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
        assertTrue(state.hasVar("FISH"));
        assertTrue(state.getVar("FISH").boolVal());
        assertEquals("", state.getStdOut());
        assertEquals("", state.getStdErr());
    }

    public void testIHasAWithRealValue() {
        CompilerState cs = new MockCompilerState();
        cmd = CommandFactory.instance().getCommand("I HAS A FISH ITZ 50.01");
        cmd.compile(cs);

        MockExecutionState state = new MockExecutionState("");
        state.setReady(true);
        cmd.execute(state);
        assertTrue(state.isReady());
        assertEquals(0, state.getErrorCount());
        assertTrue(state.hasVar("FISH"));
        assertEquals(new BigDecimal("50.01"), state.getVar("FISH").numVal());
        assertEquals("", state.getStdOut());
        assertEquals("", state.getStdErr());
    }
}
