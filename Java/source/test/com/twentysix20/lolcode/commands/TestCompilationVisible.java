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
import com.twentysix20.lolcode.MockCompilerState;
import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;
import com.twentysix20.lolcode.command.VisibleCommand;
import com.twentysix20.lolcode.exception.ExpressionCompilationException;

public class TestCompilationVisible extends TstCaseBaseCommand {
    public void testVisible() {
        CompilerState cs = new MockCompilerState();
        VisibleCommand cmd = (VisibleCommand)CommandFactory.instance().getCommand("VISIBLE \"HAI WORLD!!1!\"");
        cmd.compile(cs);
        assertTrue(cmd.hasLineBreak());
        assertFalse(cmd.isWriteError());
    }

    public void testVisibleWithPoint() {
        CompilerState cs = new MockCompilerState();
        VisibleCommand cmd = (VisibleCommand)CommandFactory.instance().getCommand("VISIBLE \"HAI WORLD!!1!\"!");
        cmd.compile(cs);
        assertFalse(cmd.hasLineBreak());
        assertFalse(cmd.isWriteError());
    }

    public void testVisibleWithPointAndSpaces() {
        CompilerState cs = new MockCompilerState();
        VisibleCommand cmd = (VisibleCommand)CommandFactory.instance().getCommand("VISIBLE \"HAI WORLD!!1!\"  !");
        cmd.compile(cs);
        assertFalse(cmd.hasLineBreak());
        assertFalse(cmd.isWriteError());
    }

    public void testVisibleNoArg() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("VISIBLE ");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (ExpressionCompilationException e) {
            // yay
        }
    }

    public void testVisibleTwoPoints() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("VISIBLE \"HAI\"!!");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (ExpressionCompilationException e) {
            // yay
        }
    }

    public void testVisibleTwoExpressions() {
        CompilerState cs = new MockCompilerState();
        VisibleCommand cmd = (VisibleCommand)CommandFactory.instance().getCommand("VISIBLE \"HAI\" \"WORLD\"");
        cmd.compile(cs);
        assertTrue(cmd.hasLineBreak());
        assertFalse(cmd.isWriteError());
    }
}
