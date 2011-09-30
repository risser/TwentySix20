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
* Created on Jun 18, 2007
*/
package com.twentysix20.lolcode;

import com.twentysix20.lolcode.Executor;
import com.twentysix20.lolcode.Program;
import com.twentysix20.lolcode.command.HaiCommand;
import com.twentysix20.lolcode.command.KthxbyeCommand;
import com.twentysix20.testutil.TestCase2620;

public class ExecutorTestCase extends TestCase2620 {
    public void testHaiBye() {
        Program program = new Program();
        MockExecutionState state = new MockExecutionState();
        MockCompilerState cstate = new MockCompilerState();
        HaiCommand haiCommand = new HaiCommand();
        KthxbyeCommand kthxbyeCommand = new KthxbyeCommand();
        haiCommand.compile(cstate);
        kthxbyeCommand.compile(cstate);
        program.addCommand(haiCommand);
        program.addCommand(kthxbyeCommand);
        
        Executor ex = new Executor(state);
        ex.execute(program);

        assertEquals(0, state.getErrorCount());
    }
}