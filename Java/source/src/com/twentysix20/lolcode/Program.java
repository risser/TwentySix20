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

import java.util.ArrayList;
import java.util.List;

import com.twentysix20.lolcode.command.Command;

public class Program {
    private List commandList = new ArrayList();
    private SymbolTable symbols = new SymbolTable();

    public void addCommand(Command cmd) {
        commandList.add(cmd);
    }

    public SymbolTable getSymbols() {
        return symbols;
    }

    public List getCommands() {
        return commandList;
    }
}
