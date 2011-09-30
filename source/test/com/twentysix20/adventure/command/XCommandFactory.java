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
import com.twentysix20.adventure.command.CommandFactory;
import com.twentysix20.util.StringUtil;

public class XCommandFactory implements CommandFactory {
    private static XCommandFactory me = new XCommandFactory();
    public static XCommandFactory instance() {
        return me;        
    }
    
    public Command getCommand(String which) {
        if (StringUtil.isEmpty(which)) throw new IllegalArgumentException("Can't get command with blank or null string.");
        if (which.equals("Plus1"))
            return new XPlusOneCommand();
        else if (which.equals("Minus1"))
            return new XMinusOneCommand();
        else
            throw new IllegalArgumentException("No command for code: "+which);
    }
    
    public XCommand getXCommand(String which) {
        return (XCommand)getCommand(which);
    }

}
