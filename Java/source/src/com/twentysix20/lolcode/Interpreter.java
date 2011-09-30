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
* Created on Jun 2, 2007
*/
package com.twentysix20.lolcode;

import java.io.IOException;

import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;

public class Interpreter {

    private CommandFactory cmdFactory = CommandFactory.instance();
    private CompilerState state;

    public Interpreter() {
        state = new CompilerState();
    }

    public Interpreter(InputOutput io) {
//        state = new CompilerState(io);
    }

    public void execute(String code) {
        execute(code.split("\\n"));
    }

    public void execute(String[] code) {
        for (int i = 0; i < code.length; i++) {
            String line = code[i];
            interpret(line);
        }
    }

    public void interpret(String line) {
        line = line.trim();
        if (!"".equals(line)) {
            Command cmd = cmdFactory.getCommand(line);
//            cmd.execute(state);
            // if (state.hasErrors()) {
            // Set errors = state.getErrors();
            // state.clearStdErr();
            // throw new RuntimeException("Errors: "+errors);
            // }
            // String out = state.getStdOut();
            // if (!StringUtil.isEmpty(out)) {
            // System.out.println(out);
            // state.clearStdOut();
            //            }
        }
//        state.upPC();
    }
    
    public static void main (String[] args) throws IOException {
        Interpreter interpreter = new Interpreter();
        StringBuffer buf = new StringBuffer();
        buf.append("HAI\n");
        buf.append("CAN HAS STDIO?\n");
        buf.append("VISIBLE \"HAI WORLD\"\n");
        buf.append("KTHXBYE\n");
        interpreter.execute(buf.toString());
    }
}