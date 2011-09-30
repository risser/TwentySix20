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
* Created on May 30, 2007
*/
package com.twentysix20.lolcode.command;

import com.twentysix20.lolcode.CompilerState;
import com.twentysix20.lolcode.ExecutionState;

public interface Command {
    public void execute(ExecutionState state);
    public String token();
    public void setLine(String string);
    public void compile(CompilerState state);
    public boolean isCompiled();
}
