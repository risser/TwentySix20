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

import junit.framework.TestCase;

public class CommandPointerTestCase extends TestCase {

    public static void main(String[] args) {
        junit.textui.TestRunner.run(CommandPointerTestCase.class);
    }
/*
    public void testSingleCommand() throws Exception {
        XCommandParameter x = new XCommandParameter(2);
        CommandStack stack = new GenericCommandStack();
        stack.stackCommand(XCommandFactory.instance().getXCommand("Plus1"));
        stack.executeNext(x);
        assertEquals(3, x.getX());
    }

    public void testTwoCommands() throws Exception {
        XCommandParameter x = new XCommandParameter(2);
        CommandStack stack = new GenericCommandStack();
        stack.stackCommand(XCommandFactory.instance().getXCommand("Plus1"));
        stack.stackCommand(XCommandFactory.instance().getXCommand("Plus1"));
        
        stack.executeNext(x);
        assertEquals(3, x.getX());
        stack.executeNext(x);
        assertEquals(4, x.getX());
    }

    public void testCommandOneLevel() throws Exception {
        XCommandParameter x = new XCommandParameter(2);
        CommandStack babystack = new GenericCommandStack();
        babystack.stackCommand(XCommandFactory.instance().getXCommand("Plus1"));
        babystack.stackCommand(XCommandFactory.instance().getXCommand("Plus1"));
        CommandStack daddyStack = new GenericCommandStack();
        
        daddyStack.stackCommand(XCommandFactory.instance().getXCommand("Plus1"));
        daddyStack.stackCommand(babystack);
        daddyStack.stackCommand(XCommandFactory.instance().getXCommand("Plus1"));

        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        daddyStack.executeNext(x);

        assertEquals(6, x.getX());
    }

    public void testCommandMultiLevel() throws Exception {
        XCommandParameter x = new XCommandParameter(2);
        XCommandFactory factory = XCommandFactory.instance();

        CommandStack plusMinus = new GenericCommandStack();
        plusMinus.stackCommand(factory.getXCommand("Plus1"));
        plusMinus.stackCommand(factory.getXCommand("Minus1"));

        CommandStack plus2 = new GenericCommandStack();
        plus2.stackCommand(factory.getXCommand("Plus1"));
        plus2.stackCommand(factory.getXCommand("Plus1"));
        plus2.stackCommand(plusMinus);

        CommandStack minus = new GenericCommandStack();
        minus.stackCommand(factory.getXCommand("Minus1"));
        
        CommandStack plus1 = new GenericCommandStack();
        plus1.stackCommand(minus);
        plus1.stackCommand(factory.getXCommand("Plus1"));
        plus1.stackCommand(factory.getXCommand("Plus1"));
        
        CommandStack daddyStack = new GenericCommandStack();
        daddyStack.stackCommand(plus1);
        daddyStack.stackCommand(plus2);

        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        daddyStack.executeNext(x);

        assertEquals(5, x.getX());
    }

    public void testCommandMultiLevel_All() throws Exception {
        XCommandParameter x = new XCommandParameter(2);
        XCommandFactory factory = XCommandFactory.instance();

        CommandStack plusMinus = new GenericCommandStack();
        plusMinus.stackCommand(factory.getXCommand("Plus1"));
        plusMinus.stackCommand(factory.getXCommand("Minus1"));

        CommandStack plus2 = new GenericCommandStack();
        plus2.stackCommand(factory.getXCommand("Plus1"));
        plus2.stackCommand(factory.getXCommand("Plus1"));
        plus2.stackCommand(plusMinus);

        CommandStack minus = new GenericCommandStack();
        minus.stackCommand(factory.getXCommand("Minus1"));
        
        CommandStack plus1 = new GenericCommandStack();
        plus1.stackCommand(minus);
        plus1.stackCommand(factory.getXCommand("Plus1"));
        plus1.stackCommand(factory.getXCommand("Plus1"));
        
        CommandStack daddyStack = new GenericCommandStack();
        daddyStack.stackCommand(plus1);
        daddyStack.stackCommand(plus2);

        daddyStack.executeAll(x);

        assertEquals(5, x.getX());
    }

    public void testCommandMultiLevel_All_Twice() throws Exception {
        XCommandParameter x = new XCommandParameter(2);
        XCommandFactory factory = XCommandFactory.instance();

        CommandStack plusMinus = new GenericCommandStack();
        plusMinus.stackCommand(factory.getXCommand("Plus1"));
        plusMinus.stackCommand(factory.getXCommand("Minus1"));

        CommandStack plus2 = new GenericCommandStack();
        plus2.stackCommand(factory.getXCommand("Plus1"));
        plus2.stackCommand(factory.getXCommand("Plus1"));
        plus2.stackCommand(plusMinus);

        CommandStack minus = new GenericCommandStack();
        minus.stackCommand(factory.getXCommand("Minus1"));
        
        CommandStack plus1 = new GenericCommandStack();
        plus1.stackCommand(minus);
        plus1.stackCommand(factory.getXCommand("Plus1"));
        plus1.stackCommand(factory.getXCommand("Plus1"));
        
        CommandStack daddyStack = new GenericCommandStack();
        daddyStack.stackCommand(plus1);
        daddyStack.stackCommand(plus2);

        daddyStack.executeAll(x);
        assertEquals(5, x.getX());
        daddyStack.executeAll(x);
        assertEquals(8, x.getX());
    }
    
    public void testStack() throws Exception {
        XCommandParameter x = new XCommandParameter(2);
        XCommandFactory factory = XCommandFactory.instance();

        CommandStack plus2 = new GenericCommandStack();
        plus2.stackCommand(factory.getXCommand("Plus1"));
        plus2.stackCommand(factory.getXCommand("Plus1"));

        CommandStack minus1 = new GenericCommandStack();
        minus1.stackCommand(factory.getXCommand("Minus1"));
        
        CommandStack daddyStack = new GenericCommandStack();
        daddyStack.stackCommand(minus1);
        daddyStack.stackCommand(plus2);

        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        assertEquals(4, x.getX());
        daddyStack.executeNext(x);
        assertEquals(3, x.getX());
    }
    
    public void testAdd() throws Exception {
        XCommandParameter x = new XCommandParameter(2);
        XCommandFactory factory = XCommandFactory.instance();

        CommandStack plus2 = new GenericCommandStack();
        plus2.stackCommand(factory.getXCommand("Plus1"));
        plus2.stackCommand(factory.getXCommand("Plus1"));

        CommandStack minus1 = new GenericCommandStack();
        minus1.stackCommand(factory.getXCommand("Minus1"));
        
        CommandStack daddyStack = new GenericCommandStack();
        daddyStack.addCommand(minus1);
        daddyStack.addCommand(plus2);

        daddyStack.executeNext(x);
        assertEquals(1, x.getX());
        daddyStack.executeNext(x);
        daddyStack.executeNext(x);
        assertEquals(3, x.getX());
    }
    */
}
