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

public class PuzzleRunner {
    static BoardListener listener = new TextAreaBoardListener();

    public static void main(String[] args) {
//        runY();
//      runSeahorse();
        runBigTihng();
//        runClown();
    }

    private static void runY() {
        runPuzzle(3,3,"1,1 | 1 | 1","1 | 2 | 1");
    }

    private static void runBigTihng() {
        runPuzzle(60,35,
                "6 | 13 | 3 4 4 | 4 ,1 ,3| 5 2 1 2 | " + 
                "5 4 5 | 22 | 4 16 | 3 5 10 | 1 2 3 13 |"+
                "2 19 | 4 16 | 3 4 4 3| 2 2 2 2| 2 2 3 3|"+
                "2 3 4 4| 3 5 11| 4 6 9 | 3 4 8 | 2 4 5 1|"+
                "3 6 5 1|15 1 4| 9 5 1 6 | 3 3 8 3 3 | 2 2 7 2 1|" + 
                "1 3 7 1 1| 2 12 1 1 | 1 11 2 2| 11 6| 8 2 2|"+
                "4 | 4 2 1 | 2 2 2 2 3 | 3 2 3 3 5| 1 1 2 2 1 2|"+
                "2 2 1 1 1 1| 2 2 2 2 2 2| 4 3 3|0|19|"+
                "5 4 | 3 3 | 7 6 | 1 21 1 | 1 16 1 |"+
                "1 1 | 1 1 | 1 1 | 1 1 | 2 2 |"+
                "3 3 | 5 5 | 1 13 | 2 2 | 2 2 |"+
                "2 3 | 3 4| 15 | 15 | 17",

                "5 | 3 3 | 5 4 | 4 1 6 | 3 2 4 |"+
                "4 1 5 3 | 5 2 6 2 2 | 3 6 2 5 3 2|3 7 2 6 1 1 8|2 3 8 4 3 1 1 2 2|"+
                "1 17 2 2 2 3 1| 2 8 8 3 2 2 4 2| 2 4 3 13 3 2 2 1| 12 13 2 3 1 1| 3 8 2 3 5 2 3 9|"+
                "2 11 1 4 3 1 2 1 7 | 2 12 2 4 2 2 1 2 1 4 | 3 8 9 5 1 1 1 2 1 3|12 14 2 2 1 2 1 3|3 7 13 2 3 1 2 1 3|"+
                "11 13 4 1 2 1 3 | 9 6 6 2 1 1 2 1 3 | 11 4 2 2 1 2 1 3|7 4 3 1 2 1 3|4 2 1 2 1 3|"+
                "4 4 1 2 1 4|2 2 1 2 1 5| 5 2 1 1 2 9| 3 2 3 2 1 2 9| 3 1 4 2 2 1 1|"+
                "2 2 2 2 1|2 2 4 2|3 1 3 1|2 2 2 2|5 8");
    }

    private static void runSeahorse() {
        runPuzzle(30,30,
                "2,2,1 | 2,2,2 | 2,2,2,2 | 3,11 | 6,2,4,2 | "+
                "3,3,1,8 | 6,10 | 3,7,3 | 2,2,3,2,2 | 4,2,2,2,1 | "+
                "5,1,2,3,2 | 5,2,13 | 4,3,4,2,2,1 | 5,7,2,5 | 6,14,5 | "+
                "9,3,7 | 10,2,5 | 5,1,3,1,3 | 7,1,3 | 1,1,2,3 | "+
                "1,1,1,3 | 1,1,2,2,3 | 3,2,1,8 | 7,5,4 | 11,2,4 | "+
                "4,1,1,1,2,3 | 8,1,2,3 | 9,2,3,2 | 3,5,1,3,3 | 2,6,2,3,2",
                "5 | 8 | 8 | 1,1,1,6 | 1,2,2,2,2,4 | "+
                "2,2,2,3,7 | 3,16,4 | 2,13,8 | 8,8,1,3 | 4,1,1,2,4,1,1 | "+
                "1,3,3,4 | 2,2,11,5 | 5,2,6,3,1,3 | 3,2,6,3,1 | 1,1,1,6,3 | "+
                "2,2,2,2,3,1,2 | 4,3,5,3,1,3 | 2,4,7,4,3 | 1,8,2,4,3 | 2,5,3,1,4,2 | "+
                "6,2,1,2,4,1 | 5,2,4,4 | 4,3,2,5 | 7,2,4 | 5,2,2,2 | "+
                "2,1,5 | 7 | 8 | 2,4 | 4");
    }

    private static void runClown() {
        runPuzzle(35,35,
                "4,3|" +
                "6,5|" +
                "10,2,4|" +
                "10,7|" +
                "2,1,1,3,7|" +
                "2,10,3,5|" +
                "3,1,3,1,1,2,2,3,3|" +
                "1,1,13,1,1,1|" +
                "1,1,13,1,3|" +
                "4,3,1,1,1,1,1,1,3,3|" +
                "3,1,1,1,1,1,1,1,4,2|" +
                "1,1,5,1,1,1,1,4,1,1|" +
                "1,2,1,8,3,1,1,1|" +
                "3,2,2,3|" +
                "2,2,2,2,3,2|" +
                "3,1,5,5,1,3|" +
                "2,5,1,1,1,1,1,1,5,2|" +
                "1,3,1,5,5,1,3,1|" +
                "1,5,1,1,1,1,1,1,5,1|" +
                "1,1,1,5,5,1,1,2|" +
                "2,1,2,1,2|" +
                "3,3,4,2,3|" +
                "1,1,4,1,1|" +
                "2,2,2,2,2|" +
                "2,2,3,3,2,2|" +
                "1,1,1,5,5,1,1,1|" +
                "1,2,1,2,6,2,1,1,1|" +
                "2,2,3,4,3,2,2|" +
                "2,2,1,3,3,1,2,2|" +
                "4,2,8,2,4|" +
                "4,5|" +
                "4,3,8|" +
                "4,13,5|" +
                "10,10|" +
                "8,8|",

                "3|" +
                "4,2,2|" +
                "1,1,1,2,4|" +
                "2,3,1,1,2,2|" +
                "1,1,3,3,2|" +
                "1,2,4,2,1,2|" +
                "4,1,2,1,3,1,1,4|" +
                "1,1,1,1,1,2,2,4|" +
                "7,2,6,5,5|" +
                "1,1,3,1,3,2|" +
                "6,3,3|" +
                "2,2,5,3,5|" +
                "11,1,1,1,5,4|" +
                "7,1,6,2,3,4|" +
                "3,8,2,1,1,3,2,2|" +
                "6,2,1,5,2,3,1,1|" +
                "4,8,4,2,1,1|" +
                "6,2,1,4,2,1,1|" +
                "4,8,5,2,3,1,1|" +
                "5,2,2,1,1,3,2,2|" +
                "11,6,2,3,4|" +
                "7,1,1,1,1,5,4|" +
                "6,5,3,5|" +
                "1,2,4,2|" +
                "9,1,6|" +
                "1,3,2,5,5,5|" +
                "5,1,1,1,1,2,7|" +
                "1,1,3,1,3,1,4|" +
                "3,4,2,4,2,1,2|" +
                "5,1,1,3,3,2|" +
                "2,4,2,3,1,1,2,2|" +
                "7,1,1,1,2,4|" +
                "7,3,2,2|" +
                "5,4|" +
                "3|"
                );
    }

    private static void runPuzzle(int numRows, int numCols, String rowStr, String colStr) {
        Board board = new Board(listener, numRows, numCols);

        String[] rowArray = rowStr.split("\\s*\\|\\s*");
        for (String s : rowArray) {
            String[] ss = s.split("\\s*[ ,]\\s*");
            int[] values = new int[ss.length];
            for (int i = 0; i < ss.length; i++)
                values[i] = Integer.parseInt(ss[i]);
            board.addRow(new Line(values));
        }

        String[] colArray = colStr.split("\\s*\\|\\s*");
        for (String s : colArray) {
            String[] ss = s.split("\\s*[, ]\\s*");
            int[] values = new int[ss.length];
            for (int i = 0; i < ss.length; i++)
                values[i] = Integer.parseInt(ss[i]);
            board.addColumn(new Line(values));
        }

        board.process();
    }

}
