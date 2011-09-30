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

public interface BoardListener {
    public void setBoard(Board board);

    public void rowAdded(Line newLine, int newRow, TraySet rowTrays);
    public void columnAdded(Line newLine, int newCol, TraySet colTrays);

    public void startProcessing();

    public void rowUpdated(int row);
    public void finishedRows();

    public void columnUpdated(int col);
    public void finishedColumns();

    public void processingComplete(boolean solved, long steps, long timeInMillis);
}
