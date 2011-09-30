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
* Created on Jul 2, 2007
*/
package com.twentysix20.lolcode.exception;

import com.twentysix20.lolcode.ExecutionState;
import com.twentysix20.lolcode.command.Command;

public class UncompiledCommandException extends ExecutionException {

    public UncompiledCommandException(Command command, ExecutionState state) {
        super(command, state, "Cannot execute command; it has not been compiled.");
    }
}