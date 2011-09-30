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
* Created on Jun 11, 2007
*/
package com.twentysix20.lolcode;

import java.io.StringReader;
import java.io.StringWriter;

import com.twentysix20.lolcode.ExecutionState;
import com.twentysix20.lolcode.InputOutput;

public class MockExecutionState extends ExecutionState {
    private StringWriter sOut;
    private StringWriter sErr;

    public MockExecutionState(String input) {
        super();
        sOut = new StringWriter();
        sErr = new StringWriter();
        InputOutput io = new InputOutput(new StringReader(input), sOut, sErr);
        setInputOutput(io);
    }

    public MockExecutionState() {
        this("");
    }

    public String getStdOut() {
        return sOut.toString();
    }
    public String[] getStdOutArray() {
        return sOut.toString().split("\\n");
    }

    public String getStdErr() {
        return sErr.toString();
    }
    public String[] getStdErrArray() {
        return sErr.toString().split("\\n");
    }
}