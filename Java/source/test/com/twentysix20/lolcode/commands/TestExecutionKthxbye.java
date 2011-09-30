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

public class TestExecutionKthxbye extends TstCaseBaseCommand {
    protected Command kthxbye;
    
    public void setUp() {
        CompilerState cs = new MockCompilerState();
        kthxbye = CommandFactory.instance().getCommand("KTHXBYE");
        kthxbye.compile(cs);
    }

    public void testNoCompile() {
        assertNoCompile("KTHXBYE");
    }

    public void testKthxbye() {
        ExecutionState es = new MockExecutionState();
        es.setReady(true);
        kthxbye.execute(es);
        assertFalse(es.isReady());
    }

    public void testBadKthxbye() {
        ExecutionState es = new MockExecutionState();
        es.setReady(false);
        kthxbye.execute(es);
        assertFalse(es.isReady());
        assertEquals(2, es.getErrorCount());
    }
}
