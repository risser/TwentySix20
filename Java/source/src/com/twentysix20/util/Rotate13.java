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
* Created on Jul 26, 2008
*/
package com.twentysix20.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Rotate13 {
    static public String rotate(String incoming) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < incoming.length(); i++) {
            char c = incoming.charAt(i);
            if (((c >= 'A') && (c <= 'M')) ||
                    ((c >= 'a') && (c <= 'm')))
                buf.append((char)(c + 13));
            else if (((c >= 'N') && (c <= 'Z')) ||
                    ((c >= 'n') && (c <= 'z')))
                buf.append((char)(c - 13));
            else
                buf.append(c);
        }
        return buf.toString();
    }

    public static void main(String[] args) throws Exception {
        InputStreamReader isr = new InputStreamReader( System.in );
        BufferedReader stdin = new BufferedReader( isr );
        System.out.println("Enter:");
        String input;
        while(!"".equals(input = stdin.readLine())) {
            System.out.println("RESULT="+Rotate13.rotate(input));
        }
    }
}
