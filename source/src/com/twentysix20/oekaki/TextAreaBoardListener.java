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

public class TextAreaBoardListener implements BoardListener {
    private Board board;
    private TextAreaOutput output;

    public void setBoard(Board board) {
        this.board = board;
    }

    public void rowAdded(Line newLine, int newRow, TraySet rowTrays) {
        System.out.println("TraySet for row "+newRow+" created: "+newLine+" : "+rowTrays.size()+" trays.");
    }

    public void columnAdded(Line newLine, int newCol, TraySet colTrays) {
        System.out.println("TraySet for column "+newCol+" created: "+newLine+" : "+colTrays.size()+" trays.");
    }

    public void startProcessing() {
        output = new TextAreaOutput();
    }

    public void rowUpdated(int row) {
        output.setText(board.prettyPrint());
    }

    public void finishedRows() {
    // TODO Auto-generated method stub

    }

    public void columnUpdated(int col) {
        output.setText(board.prettyPrint());
    }

    public void finishedColumns() {
    // TODO Auto-generated method stub

    }

    public void processingComplete(boolean solved, long steps, long timeInMillis) {
        String caption = "----\n"+(solved ? "SOLVED!" : "FAIL!")+"\n";
        caption += " - in "+steps+" steps.\n";
        long sec = (timeInMillis / 1000);
        caption += " - in "+sec+" second"+(sec == 1 ? "" : "s")+".\n";
        output.setText(board.prettyPrint()+caption);
    }

}
