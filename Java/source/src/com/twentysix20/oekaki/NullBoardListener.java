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

public class NullBoardListener implements BoardListener {

    public void setBoard(Board board) {
        // TODO Auto-generated method stub
        
    }

    public void rowAdded(Line newLine, int newRow, TraySet rowTrays) {
        // TODO Auto-generated method stub
        
    }

    public void columnAdded(Line newLine, int newCol, TraySet colTrays) {
        // TODO Auto-generated method stub
        
    }

    public void startProcessing() {
        // TODO Auto-generated method stub
        
    }

    public void rowUpdated(int row) {
        // TODO Auto-generated method stub
        
    }

    public void finishedRows() {
        // TODO Auto-generated method stub
        
    }

    public void columnUpdated(int col) {
        // TODO Auto-generated method stub
        
    }

    public void finishedColumns() {
        // TODO Auto-generated method stub
        
    }

    public void processingComplete(boolean solved, long steps, long timeInMillis) {
        System.out.println("DONE.");
    }
}
