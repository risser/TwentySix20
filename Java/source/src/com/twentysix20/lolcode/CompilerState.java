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
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.twentysix20.lolcode.exception.DuplicateIdentifierException;

public class CompilerState {
//    protected boolean ready = false;
//    protected int pc = 0;
    protected Set errors = new HashSet();
//    private StringBuffer stdOut = new StringBuffer();
//    private StringBuffer stdErr = new StringBuffer();
//    protected InputOutput inOut;
    protected List imports = new ArrayList();
    protected List vars = new ArrayList();
    protected List commands = new ArrayList();

//    public boolean isReady() {
//        return ready;
//    }
//
//    public void setReady(boolean ready) {
//        this.ready = ready;
//    }

    public void clear() {
//        ready = false;
//        clearErrors();
//        clearStdOut();
//        clearStdErr();
        clearImports();
        clearVars();
    }

    public void clearVars() {
        vars = new ArrayList();
    }

    public void addVar(String name) {
        if (vars.contains(name)) throw new DuplicateIdentifierException(name);
        vars.add(name);
    }
    public boolean hasVar(String name) {
        return vars.contains(name);
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
}