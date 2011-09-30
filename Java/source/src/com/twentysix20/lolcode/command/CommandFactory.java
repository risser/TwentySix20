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

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.twentysix20.lolcode.Constants;

public class CommandFactory {
    private static CommandFactory me = new CommandFactory();
    private Map commandMap = new HashMap();
    private List listOfCommands;

    static public CommandFactory instance() {
        if (me == null)
            me = new CommandFactory();
        return me;
    }
    
    private CommandFactory() {
        addCommands();
    }

    protected void addCommands() {
        register(new CanHasCommand());
        register(new HaiCommand());
        register(new IHasACommand());
        register(new KthxbyeCommand());
        register(new VisibleCommand());
        commandMap.put(Constants.COMMAND_INVISIBLE, VisibleCommand.class);
    }
    
    protected void register(Command command) {
        commandMap.put(command.token(), command.getClass());
    }

    public List getListOfCommands() {
        if (listOfCommands == null) {
            listOfCommands = new ArrayList(commandMap.keySet());
        }
        return listOfCommands;
    }

    public Command getCommand(String line) {
        Iterator itrTokens = getListOfCommands().iterator();
        Class cmdClass = null;
        line = line.trim();
        String uprLine = line.toUpperCase();
        String token = null;
        while (itrTokens.hasNext()) {
            token = (String)itrTokens.next();
            if (uprLine.startsWith(token)) {
                cmdClass = (Class)commandMap.get(token);
                break;
            }
        }
        if (cmdClass == null)
            throw new IllegalArgumentException("No command found for line: "+line);

        Constructor cons;
        try {
            cons = cmdClass.getConstructor(null);
            Command cmd = (Command)cons.newInstance(null);
            cmd.setLine(line.substring(token.length()).trim());
            if (Constants.COMMAND_INVISIBLE.equals(token))
                ((VisibleCommand)cmd).setWriteError(true);
            
            return cmd;
        } catch (Exception e) {
            // if this fails, GTFO
            throw new RuntimeException(e);
        }
    }
}