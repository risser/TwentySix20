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
* Copyright (c) 2005 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Nov 22, 2005
*/
package com.twentysix20.adventure.command;

import com.twentysix20.adventure.command.Command;
import com.twentysix20.adventure.command.CommandParameter;
import com.twentysix20.adventure.command.CommandResult;

abstract public class XCommand implements Command {
    abstract public CommandResult execute(XCommandParameter param);

    public CommandResult execute(CommandParameter param) {
        if (!(param instanceof XCommandParameter)) throw new IllegalArgumentException("XCommands only work with XCommand Parameters.");
        return execute((XCommandParameter)param);
    }

}
