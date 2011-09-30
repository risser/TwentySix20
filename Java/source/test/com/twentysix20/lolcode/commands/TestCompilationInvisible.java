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
import com.twentysix20.testutil.TestCase2620;

public class TestCompilationInvisible extends TestCase2620 {
    public void testInvisible() {
        CompilerState cs = new MockCompilerState();
        VisibleCommand cmd = (VisibleCommand)CommandFactory.instance().getCommand("INVISIBLE \"HAI WORLD!!1!\"");
        cmd.compile(cs);
        assertTrue(cmd.hasLineBreak());
        assertTrue(cmd.isWriteError());
    }

    public void testInvisibleNoArg() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("INVISIBLE ");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (ExpressionCompilationException e) {
            // yay
        }
    }

    public void testInvisibleWithPoint() {
        CompilerState cs = new MockCompilerState();
        VisibleCommand cmd = (VisibleCommand)CommandFactory.instance().getCommand("INVISIBLE \"HAI WORLD!!1!\"!");
        cmd.compile(cs);
        assertFalse(cmd.hasLineBreak());
        assertTrue(cmd.isWriteError());
    }

    public void testInvisibleWithPointAndSpaces() {
        CompilerState cs = new MockCompilerState();
        VisibleCommand cmd = (VisibleCommand)CommandFactory.instance().getCommand("INVISIBLE \"HAI WORLD!!1!\"  !");
        cmd.compile(cs);
        assertFalse(cmd.hasLineBreak());
        assertTrue(cmd.isWriteError());
    }

    public void testInvisibleTwoPoints() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("INVISIBLE \"HAI\"!!");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (ExpressionCompilationException e) {
            // yay
        }
    }
}
