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
* Copyright (c) 2008 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Dec 9, 2008
*/
package com.twentysix20.oekaki;
//File Test.java
import java.awt.*;
import java.awt.event.*;
 
public class TextAreaOutput extends Frame implements WindowListener {
    
    private final TextArea text = new TextArea
            ("", 60, 60, TextArea.SCROLLBARS_NONE);
    private final String NEWLINE = "" + '\n';
    
    public TextAreaOutput() {
        text.setEditable(true);
        text.setFont(new Font("Monospaced", Font.PLAIN, 12));
        add(text);
        setTitle("Test Application");
        setResizable(false);
        addWindowListener(this);
        pack();
        setVisible(true);
    }
    
    public void print(String s) {
        text.append(s);
    }
    
    public void println(String s) {
        text.append(s);
        text.append(NEWLINE);
    }
    
    public void cls() {
        text.setText("");
    }
    
    public void setText(String s) {
        text.setText(s);
    }
    
    public void kill() {
        dispose();
    }
    
    
    // We must implement the following as we are
    // implementing WindowListener
    
    public void windowDeactivated(WindowEvent e) {}
    public void windowDeiconified(WindowEvent e) {}
    public void windowIconified(WindowEvent e) {}
    public void windowOpened(WindowEvent e) {}
    
    public void windowActivated(WindowEvent e) {}
    
    public void windowClosed(WindowEvent e) {
        System.exit(0);
    }
    
    public void windowClosing(WindowEvent e) {
        dispose();
    }
            
    
    public static void main(String[] args) {
        TextAreaOutput t = new TextAreaOutput();
        t.println("Hello World!");
        t.print("Same line ");
        t.println("Test");
        for (int i = 0; i < 256; i++)
            t.print(""+(char)i);
        try {
            Thread.sleep(5000); // Wait 5 seconds
        } catch (InterruptedException e) {
            // Do nothing, not critical to
            // be interrupted
        }
        t.cls();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // Do nothing, not critical to
            // be interrupted
        }
        t.kill();        
    }
}
