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
* Created on Jun 29, 2007
*/
package com.twentysix20.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Date;

import com.twentysix20.util.DateUtil;

public class Pinger {

    private static final int WAIT_TIME_IN_SECS = 120;

    public static void main(String[] args) throws IOException {
        while (true) {
            BufferedReader in = null;
            long time = System.currentTimeMillis();
            try {
                long num = (long)(Math.random() * 100000);
                System.out.print(DateUtil.formatDate(new Date(), "MM/dd/yyyy hh:mm:ss")
                        + " : " + num + " : ");
                URL source = new URL("http://en.wikipedia.org/wiki/Special:Search?search="+num+"&go=Go");
                in = new BufferedReader(new InputStreamReader(source.openStream(),"UTF-8"));
                String line = in.readLine();
                System.out.println(System.currentTimeMillis() - time + "ms : Worked.");
            } catch (Exception e) {
                System.out.println(System.currentTimeMillis() - time + "ms : Didn't worked : " + e);
            } finally {
                if (in != null)
                    in.close();
            }
            try {
                Thread.sleep(WAIT_TIME_IN_SECS * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
