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
* Copyright (c) 2006 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Mar 25, 2006
*/
package com.twentysix20.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.twentysix20.util.FileUtil;

public class FileScanner {
    private BufferedReader reader;
    private List listenerList = new ArrayList();

    public FileScanner(String fileName) throws IOException {
        super();
        Charset c = FileUtils.determineCharSet(fileName);
        reader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), c));
    }

    public void addListener(FileScannerListener listener) {
        listenerList.add(listener);
    }
    
    public void removeListener(FileScannerListener listener) {
        listenerList.remove(listener);
    }
    
    public void scanFile() throws IOException {
        String line;
        while ((line = reader.readLine()) != null) {
            Iterator itrListeners = listenerList.iterator();
            while (itrListeners.hasNext())
                ((FileScannerListener)itrListeners.next()).handleLine(line);
        }
        Iterator itrListeners = listenerList.iterator();
        while (itrListeners.hasNext())
            ((FileScannerListener)itrListeners.next()).scanComplete();
    }
}
