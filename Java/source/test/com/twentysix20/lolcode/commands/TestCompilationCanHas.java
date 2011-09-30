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
import com.twentysix20.lolcode.SymbolTable;
import com.twentysix20.lolcode.TstCaseLolcode;
import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;
import com.twentysix20.lolcode.exception.BadIdentifierException;

public class TestCompilationCanHas extends TstCaseLolcode {

    public void testCanHas() {
        CompilerState cs = new MockCompilerState();
        Command canhas = CommandFactory.instance().getCommand("CAN HAS STDIO");
        canhas.compile(cs);
        assertEquals(1, cs.getImports().size());
        assertEquals("STDIO", cs.getImports().get(0));
    }

    public void testCanHasMixedCase() {
        CompilerState cs = new MockCompilerState();
        Command canhas = CommandFactory.instance().getCommand("Can haS STDio");
        canhas.compile(cs);
        assertEquals(1, cs.getImports().size());
        assertEquals("STDio", cs.getImports().get(0));
    }

    public void testCanHasQuestion() {
        CompilerState cs = new MockCompilerState();
        Command canhas = CommandFactory.instance().getCommand("CAN HAS STDIO?");
        canhas.compile(cs);
        assertEquals(1, cs.getImports().size());
        assertEquals("STDIO", cs.getImports().get(0));
    }
    public void testBadId() {
        CompilerState cs = new MockCompilerState();
        Command canhas = CommandFactory.instance().getCommand("CAN HAS STD IO?");
        try {
            canhas.compile(cs);
            fail("Should have thrown exception.");
        } catch (BadIdentifierException e) {
            // good!
        }
    }
}
