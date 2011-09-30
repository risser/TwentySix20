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
* Created on Jun 11, 2007
*/
package com.twentysix20.lolcode;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;

public class InputOutput implements Output {
    protected BufferedReader in;
    protected PrintWriter out;
    protected PrintWriter err;

    public InputOutput() {
        this(System.in, System.out, System.err);
    }

    public InputOutput(InputStream in, PrintStream out, PrintStream err) {
        this(new InputStreamReader(in), new PrintWriter(out), new PrintWriter(err));
    }

    public InputOutput(Reader in, Writer out, Writer err) {
        this.in = (in instanceof BufferedReader ? (BufferedReader)in : new BufferedReader(in));
        this.out = (out instanceof PrintWriter ? (PrintWriter)out : new PrintWriter(out));
        this.err = (err instanceof PrintWriter ? (PrintWriter)err : new PrintWriter(err));
    }

    public void print(String s) {
        print(s, false);
    }

    public void print(String s, boolean newline) {
        out.print(s);
        if (newline)
            out.print("\n");
        if (out.checkError()) {
            throw new RuntimeException("Error occurred writing to standard output stream.");
        }
    }

    public void println(String s) {
        print(s, true);
    }

    public void printErr(String s, boolean newline) {
        err.print(s);
        if (newline)
            err.print("\n");
        if (err.checkError()) {
            throw new RuntimeException("Error occurred writing to standard error stream.");
        }
    }

    public void printErr(String s) {
        printErr(s, false);
    }

    public void printlnErr(String s) {
        printErr(s, true);
    }
}