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
* Created on Jun 25, 2007
*/
package com.twentysix20.lolcode.exception;

import com.twentysix20.lolcode.command.BaseCommand;
import com.twentysix20.lolcode.command.Command;

public class CompilerException extends RuntimeException {
    private Command command;

    public CompilerException(Command command, String message) {
        this(command, message, null);
    }

    public CompilerException(Command command, String message, RuntimeException cause) {
        super(message + " ["+(command instanceof BaseCommand ? "Line="+((BaseCommand)command).getLineNumber()+"; " : "") + "Command="+command.token()+"]", cause);
        this.command = command;
    }

    public Command getCommand() {
        return command;
    }
}
