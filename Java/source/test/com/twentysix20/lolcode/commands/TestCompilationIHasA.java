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
import com.twentysix20.lolcode.Univar;
import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;
import com.twentysix20.lolcode.command.IHasACommand;
import com.twentysix20.lolcode.exception.BadIdentifierException;
import com.twentysix20.lolcode.exception.IdentifierAlreadyDeclaredException;
import com.twentysix20.lolcode.exception.ImproperCommandConstructionException;
import com.twentysix20.lolcode.exception.MissingIdentifierException;
import com.twentysix20.lolcode.expression.ValueExpression;

public class TestCompilationIHasA extends TstCaseBaseCommand {
    public void testIHasA() {
        CompilerState cs = new MockCompilerState();
        IHasACommand cmd = (IHasACommand)CommandFactory.instance().getCommand("I HAS A FISH");
        cmd.compile(cs);
        assertHasVar(cs, "FISH");
        assertNull(cmd.getExpression());
    }

    public void testIHasAMixedCase() {
        CompilerState cs = new MockCompilerState();
        IHasACommand cmd = (IHasACommand)CommandFactory.instance().getCommand("I HAS A FisH");
        cmd.compile(cs);
        assertHasVar(cs, "FisH");
        assertNull(cmd.getExpression());
    }

    public void testIHasAWithValue() {
        CompilerState cs = new MockCompilerState();
        IHasACommand cmd = (IHasACommand)CommandFactory.instance().getCommand("I HAS A FISH ITZ 4");
        cmd.compile(cs);
        assertHasVar(cs, "FISH");
        assertNotNull(cmd.getExpression());
        assertInstanceOf(cmd.getExpression(), ValueExpression.class);
        assertEquals("4", cmd.getExpression().evaluate().strVal());
    }

    public void testIHasANoArg() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("I HAS A ");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (MissingIdentifierException e) {
            // yay
        }
    }

    public void testIHasANoArgItz() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("I HAS A ITZ 4");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (ImproperCommandConstructionException e) {
            // yay
        }
    }

    public void testIHasAValueNoItz() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("I HAS A FISH 4");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (ImproperCommandConstructionException e) {
            // yay
        }
    }

    public void testIHasAValueBadItz() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("I HAS A FISH IZ 4");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (ImproperCommandConstructionException e) {
            // yay
        }
    }

    public void testIHasANonIdentifier() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("I HAS A 4");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (BadIdentifierException e) {
            // yay
        }
    }

    public void testIHasAAlready() {
        CompilerState cs = new MockCompilerState();
        cs.addVar("ALREADY");
        Command cmd = CommandFactory.instance().getCommand("I HAS A ALREADY");
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        } catch (IdentifierAlreadyDeclaredException e) {
            // yay
        }
    }

    public void testIHasAMixedCaseIsNotSame() {
        CompilerState cs = new MockCompilerState();
        cs.addVar("ALREADY");
        IHasACommand cmd = (IHasACommand)CommandFactory.instance().getCommand("I HAS A ALreadY");
        cmd.compile(cs);
        assertHasVar(cs, "ALreadY");
        assertHasVar(cs, "ALREADY");
    }

    public void testIHasAKeyword() {
        CompilerState cs = new MockCompilerState();
        Command cmd = CommandFactory.instance().getCommand("I HAS A "+Univar.TRUE);
        try {
            cmd.compile(cs);
            fail("Should have thrown exception.");
        }
        catch (BadIdentifierException e) {
            // yay
        }
    }

    public void testIHasAWithStringValue() {
        CompilerState cs = new MockCompilerState();
        IHasACommand cmd = (IHasACommand)CommandFactory.instance().getCommand("I HAS A FISH ITZ \"YUM\"");
        cmd.compile(cs);
        assertHasVar(cs, "FISH");
        assertNotNull(cmd.getExpression());
        assertInstanceOf(cmd.getExpression(), ValueExpression.class);
        assertEquals("YUM", cmd.getExpression().evaluate().strVal());
    }

    public void testIHasAWithSpaces() {
        CompilerState cs = new MockCompilerState();
        IHasACommand cmd = (IHasACommand)CommandFactory.instance().getCommand("I HAS A FISH    ITZ    \"YUM\"  ");
        cmd.compile(cs);
        assertHasVar(cs, "FISH");
        assertNotNull(cmd.getExpression());
        assertInstanceOf(cmd.getExpression(), ValueExpression.class);
        assertEquals("YUM", cmd.getExpression().evaluate().strVal());
    }

    public void testIHasAWithItzInString() {
        CompilerState cs = new MockCompilerState();
        IHasACommand cmd = (IHasACommand)CommandFactory.instance().getCommand("I HAS A FISH ITZ \"40 ITZ 40\"");
        cmd.compile(cs);
        assertHasVar(cs, "FISH");
        assertNotNull(cmd.getExpression());
        assertInstanceOf(cmd.getExpression(), ValueExpression.class);
        assertEquals("40 ITZ 40", cmd.getExpression().evaluate().strVal());
    }
}
