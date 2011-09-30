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
package com.twentysix20.lolcode.commands;

import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;
import com.twentysix20.lolcode.command.HaiCommand;
import com.twentysix20.lolcode.command.VisibleCommand;
import com.twentysix20.testutil.TestCase2620;

public class TestCommandFactory extends TestCase2620 {
    public void testCommandGet() {
        CommandFactory f = CommandFactory.instance();
        Command c = f.getCommand("HAI");
        assertInstanceOf(c, HaiCommand.class);
    }

    public void testCommandGetCaseInsensitive() {
        CommandFactory f = CommandFactory.instance();
        Command c = f.getCommand("Hai");
        assertInstanceOf(c, HaiCommand.class);
    }

    public void testCommandVisible() {
        CommandFactory f = CommandFactory.instance();
        Command c = f.getCommand("VISIBLE \"TEST\"");
        assertInstanceOf(c, VisibleCommand.class);
    }
}
