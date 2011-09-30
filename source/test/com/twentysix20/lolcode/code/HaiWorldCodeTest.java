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
package com.twentysix20.lolcode.code;

import com.twentysix20.lolcode.Compiler;
import com.twentysix20.lolcode.Executor;
import com.twentysix20.lolcode.MockCompilerState;
import com.twentysix20.lolcode.MockExecutionState;
import com.twentysix20.lolcode.Program;
import com.twentysix20.lolcode.commands.TstCaseBaseCommand;

public class HaiWorldCodeTest extends TstCaseBaseCommand {
    public void testHaiWorld() {
        MockCompilerState cs = new MockCompilerState();
        StringBuffer buf = new StringBuffer();
        buf.append("HAI\n");
        buf.append("CAN HAS STDIO?\n");
        buf.append("VISIBLE \"HAI WORLD\"\n");
        buf.append("KTHXBYE\n");
        Compiler c = new Compiler(buf.toString());
        Program prog = c.getProgram();

        MockExecutionState es = new MockExecutionState();
        Executor executor = new Executor(es);
        executor.execute(prog);
//        assertEquals("HAI WORLD", es.getStdOut());
//        assertEquals("", es.getStdErr());
    }
}
