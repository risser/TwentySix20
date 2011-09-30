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

public class SimpleConsolePrinter implements BoardListener {
    private Board board;

    public void startProcessing() {
        System.out.println("====");
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void rowUpdated(int row) {
        System.out.printf("Updating row %2d %s\n",row,board.createTrayFromRow(row).prettyPrint());
    }

    public void columnUpdated(int col) {
        System.out.printf("Updating col %2d %s\n",col,board.createTrayFromColumn(col).prettyPrint());
    }

    public void finishedRows() {
        System.out.println("-");
    }

    public void finishedColumns() {
        System.out.println("----");
    }

    public void processingComplete(boolean solved, long steps, long timeInMillis) {
        System.out.println("SOLUTION:");
        System.out.println(board.prettyPrint());
        System.out.println("----"+(solved ? "\nSOLVED!" : "\nFAIL!"));
        System.out.println(" - in "+steps+" steps.");
        long sec = (timeInMillis / 1000);
        System.out.println(" - in "+sec+" second"+(sec == 1 ? "" : "s")+".");
    }

    public void rowAdded(Line newLine, int newRow, TraySet rowTrays) {
        System.out.println("TraySet for row "+newRow+" created: "+newLine+" : "+rowTrays.size()+" trays.");
    }

    public void columnAdded(Line newLine, int newCol, TraySet colTrays) {
        System.out.println("TraySet for column "+newCol+" created: "+newLine+" : "+colTrays.size()+" trays.");
    }

}
