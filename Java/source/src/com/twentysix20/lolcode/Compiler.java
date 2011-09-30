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

import com.twentysix20.lolcode.command.BaseCommand;
import com.twentysix20.lolcode.command.Command;
import com.twentysix20.lolcode.command.CommandFactory;
import com.twentysix20.lolcode.exception.MissingNextLineException;
import com.twentysix20.lolcode.exception.UnterminatedStringException;
import com.twentysix20.util.StringUtil;

public class Compiler {
    private String[] lines;
    private boolean inComment = false;
    private CommandFactory cmdFactory = CommandFactory.instance();

    public Compiler(String string) {
        this(string == null ? null : (" "+string).replaceAll("\n\n", "\n \n").split("\n"));
    }

    public Compiler(String[] strings) {
        lines = (strings != null ? strings : new String[]{""});
        preprocess();
        compile();
    }

    private Program compile() {
        Program program = new Program();
        for (int i = 0; i < lines.length; i++) {
            if (StringUtil.isEmpty(lines[i])) continue;

            Command cmd = cmdFactory.getCommand(lines[i]);
            if (cmd instanceof BaseCommand)
                ((BaseCommand)cmd).setLineNumber(i);
            program.addCommand(cmd);
//HOW DO EXPRESSIONS/VARIABLES GET INCLUDED?            
        }
        return program;
    }

    private void preprocess() {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = lines[i].trim();
            lines[i] = lines[i].replaceAll(Constants.ESCAPED_ESCAPECHAR, Constants.ESCAPE_CHAR);
            lines[i] = lines[i].replaceAll(Constants.ESCAPED_QUOTE, "\"");
            lines[i] = lines[i].replaceAll(":\\)", "@");
            lines[i] = lines[i].replaceAll(Constants.ESCAPED_TAB, "\t");
            lines[i] = lines[i].replaceAll(Constants.ESCAPED_BEEP, "\u0007");

//            boolean inString = false;
//            StringBuffer buf = new StringBuffer(lines[i]);
//            for (int j = 0; j < buf.length(); j++) {
//                escapeChars(buf, j);
//                checkUnterminatedStrings(lines[i], i);
//                handleSingleLineComment(i);
//            }
            handleMultiLineComment(i);
            i += conjoinLines(i);
        }
    }
//
//    private void escapeChars(StringBuffer buf, int pos) {
//        if (!buf.charAt(Constants.ESCAPE_CHAR)) return;
//        if (pos+1 >= buf.length()) throw new 
//
//        if (buf.substring(pos,pos+2).equals(Constants.ESCAPED_ESCAPECHAR
//    }

    private void handleMultiLineComment(int i) {
        if (inComment) {
            if (Constants.END_COMMENT.equals(lines[i]))
                inComment = false;
            lines[i] = "";
        }
        if (Constants.START_COMMENT.equals(lines[i])) {
            inComment = true;
            lines[i] = "";
        }
        if (Constants.END_COMMENT.equals(lines[i]))
            throw new RuntimeException("End comment with no matching begin comment.");
    }

    private void handleSingleLineComment(int i) {
        int pos = lines[i].indexOf(Constants.SINGLE_COMMENT);
        if (pos < 0) return;
        if (pos == 0) {
            lines[i] = "";
            return;
        }
        char c = lines[i].charAt(pos-1);
        if ((c == ' ') || (c == '\t')) {
            lines[i] = lines[i].substring(0,pos).trim();
        }
    }

    private void stringReplace(int i) {
        int pos = lines[i].indexOf(Constants.SINGLE_COMMENT);
        if (pos < 0) return;
        if (pos == 0) {
            lines[i] = "";
            return;
        }
        char c = lines[i].charAt(pos-1);
        if ((c == ' ') || (c == '\t')) {
            lines[i] = lines[i].substring(0,pos).trim();
        }
    }

    private int conjoinLines(int i) {
        int count = 0;
        while (lines[i].endsWith(Constants.CONTINUE_TOKEN)) {
            count++;
            if ((i + count >= lines.length) || StringUtil.isEmpty(lines[i+count]))
                throw new MissingNextLineException("Missing continued line after line " + (i + count - 1));
            int len = lines[i].length();
            int pos = len - Constants.CONTINUE_TOKEN.length();
            lines[i] = lines[i].substring(0,pos).trim() + " " + lines[i + count].trim();
            lines[i + count] = "";
        }
        return count;
    }

    private void checkUnterminatedStrings(String string, int line) {
        if (string.indexOf('\"') < 0) return;
//        if (!string.matches("([^"+Constants.ESCAPED_QUOTE+"]*"
//                                 +Constants.JAVA_QUOTE
//                           +"([^"+Constants.ESCAPED_QUOTE+"])*"
//                                 +Constants.JAVA_QUOTE
//                            +"[^"+Constants.ESCAPED_QUOTE+"]*)*"))
//            throw new UnterminatedStringException("Unterminated string on line "+(line+1));
    }

    public Program getProgram() {
        return new Program();
    }

    public String[] getLines() {
        return lines;
    }
}
