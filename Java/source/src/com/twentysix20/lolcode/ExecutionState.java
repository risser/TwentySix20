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
* Created on May 31, 2007
*/
package com.twentysix20.lolcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ExecutionState {
    protected boolean ready = false;
    protected int pc = 0;
    protected Set errors = new HashSet();
    protected InputOutput inOut;
    protected List imports = new ArrayList();
    protected Map vars = new HashMap();

    public ExecutionState() {
        inOut = new InputOutput();
    }

    public ExecutionState(InputOutput io) {
        inOut = io;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void clear() {
        ready = false;
        clearErrors();
        clearImports();
        clearVars();
    }

    public void clearVars() {
        vars = new HashMap();
    }

    public void addVar(String name, Univar value) {
        vars.put(name, value);
    }
    public Univar getVar(String name) {
        if (!hasVar(name))
            addError("Referencing unknown variable: "+name);
        return (Univar)vars.get(name);
    }

    public boolean hasVar(String name) {
        return vars.containsKey(name);
    }

    public int getErrorCount() {
        return errors.size();
    }
    public Set getErrors() {
        return errors; 
    }
    public void addError(String msg) {
        errors.add("Line "+pc+": "+msg); 
    }
    public void clearErrors() {
        errors.clear();
    }
    public boolean hasErrors() {
        return (getErrorCount() != 0);
    }

//    public String getStdOut() {
//        return stdOut.toString();
//    }
    public void writeStdOut(String s, boolean newline) {
        try {
            inOut.print(s, newline);
        } catch (RuntimeException e) {
            addError(e.getMessage());
        }
    }
//    public void clearStdOut() {
//        stdOut = new StringBuffer();
//    }
//    public void flushStdOut() {
//    }

//    public String getStdErr() {
//        return stdErr.toString();
//    }
    public void writeStdErr(String s, boolean newline) {
        try {
            inOut.printErr(s, newline);
        } catch (RuntimeException e) {
            addError(e.getMessage());
        }
    }
//    public void clearStdErr() {
//        stdErr = new StringBuffer();
//    }

    public void setInputOutput(InputOutput io) {
        inOut = io;
    }

    public void addImport(String importName) {
        imports.add(importName);
    }
    public List getImports() {
        return imports;
    }
    public void clearImports() {
        imports = new ArrayList();
    }

    public void upPC() {
        pc++;
    }
    public int getPC() {
        return pc;
    }
}