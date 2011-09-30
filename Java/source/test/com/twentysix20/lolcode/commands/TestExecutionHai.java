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
import com.twentysix20.lolcode.exception.ImproperBlockConstructionException;
import com.twentysix20.lolcode.exception.UncompiledCommandException;
import com.twentysix20.testutil.TestCase2620;

public class TestExecutionHai extends TstCaseBaseCommand {
    private Command hai;
    
    public void setUp() {
        CompilerState cs = new MockCompilerState();
        hai = CommandFactory.instance().getCommand("HAI");
        hai.compile(cs);
    }

    public void testNoCompile() {
        assertNoCompile("HAI");
    }

    public void testHai() {
        ExecutionState es = new MockExecutionState();
        hai.execute(es);
        assertTrue(es.isReady());
    }

    public void testBadHai() {
        ExecutionState es = new MockExecutionState();
        es.setReady(true);
        hai.execute(es);
        assertTrue(es.isReady());
        assertEquals(1, es.getErrorCount());
    }
}