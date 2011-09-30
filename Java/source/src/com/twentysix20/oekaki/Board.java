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
* Created on Nov 5, 2008
*/
package com.twentysix20.oekaki;

import java.util.LinkedList;
import java.util.Queue;


public class Board {
    private Line[] rows;
    private Line[] columns;
    private TraySet[] rowTrays;
    private TraySet[] colTrays;
    private CellValue[][] board;
    private Queue<Integer> rowToProcess = new LinkedList<Integer>();
    private Queue<Integer> colToProcess = new LinkedList<Integer>();
    private BoardListener listener;

    public Board(int numberOfRows, int numberOfColumns) {
        this(new NullBoardListener(), numberOfRows, numberOfColumns);
    }

    public Board(BoardListener listener, int numberOfRows, int numberOfColumns) {
        this.listener = listener;
        this.listener.setBoard(this);

        board = new CellValue[numberOfRows][numberOfColumns];
        rows = new Line[numberOfRows];
        columns = new Line[numberOfColumns];
        rowTrays = new TraySet[numberOfRows];
        colTrays = new TraySet[numberOfColumns];
        
//        Tray emptyRowTray = new Tray(getRowLength());
//        for (int i = 0; i < getNumberOfRows(); i++) {
//            applyTrayToRow(i, emptyRowTray);
//        }
//        Tray emptyColumnTray = new Tray(getColumnLength());
//        for (int i = 0; i < getNumberOfColumns(); i++) {
//            applyTrayToCol(i, emptyColumnTray);
//        }

        for (int row = 0; row < getNumberOfRows(); row++)
            for (int col = 0; col < getNumberOfColumns(); col++)
                board[row][col] = CellValue.UNKNOWN;
    }

    public void process() {
        listener.startProcessing();

        if (rows[rows.length-1] == null) throw new NullPointerException("Not enough rows entered.");
        if (columns[columns.length-1] == null) throw new NullPointerException("Not enough columns entered.");

        // preprocess
        for (int row = 0; row < getNumberOfRows(); row++) {
            int diff = getRowLength() - rows[row].actualSize();
            if ((diff < rows[row].largestValue()) || (rows[row].actualSize() == 0)) {
                TraySet set = new TraySetCreator(rows[row], getRowLength()).traySet();
                applyTrayToRow(row, set.collate(new Tray(getRowLength())));
                rowTrays[row] = set;
                listener.rowAdded(rows[row], row, rowTrays[row]);
            }
        }

        for (int col = 0; col < getNumberOfColumns(); col++) {
            int diff = getColumnLength() - columns[col].actualSize();
            if ((diff < columns[col].largestValue()) || (columns[col].actualSize() == 0)) {
                TraySet set = new TraySetCreator(columns[col], createTrayFromColumn(col)).traySet();
                applyTrayToCol(col, set.collate(new Tray(getColumnLength())));
                colTrays[col] = set;
                listener.columnAdded(columns[col], col, colTrays[col]);        
            }
        }

        long steps = 0L;
        long start = System.currentTimeMillis();
        while ((rowToProcess.size() > 0) || (colToProcess.size() > 0)) {
            while (rowToProcess.size() > 0) {
                int row = rowToProcess.poll();
                Tray boardTray = createTrayFromRow(row);
                if (boardTray.isDefinitive()) continue;
                if (rowTrays[row] == null) {
                    rowTrays[row] = new TraySetCreator(rows[row], boardTray).traySet();
                    listener.rowAdded(rows[row], row, rowTrays[row]);        
                }
                Tray updatedTray = rowTrays[row].collate(boardTray);
                applyTrayToRow(row, updatedTray);

                listener.rowUpdated(row);
                steps++;
            }
            listener.finishedRows();
            
            while (colToProcess.size() > 0) {
                int column = colToProcess.poll();
                Tray boardTray = createTrayFromColumn(column);
                if (boardTray.isDefinitive()) continue;
                if (colTrays[column] == null) {
                    colTrays[column] = new TraySetCreator(columns[column], boardTray).traySet();
                    listener.columnAdded(columns[column], column, colTrays[column]);        
                }
                Tray updatedTray = colTrays[column].collate(boardTray);
                applyTrayToCol(column, updatedTray);

                listener.columnUpdated(column);
                steps++;
            }
            listener.finishedColumns();
        }

        boolean solved = true;
        for (int row = 0; row < getNumberOfRows(); row++) {
            Tray tray = createTrayFromRow(row);
            solved &= tray.isDefinitive();
        }
        for (int column = 0; column < getNumberOfColumns(); column++) {
            Tray tray = createTrayFromColumn(column);
            solved &= tray.isDefinitive();
        }
        listener.processingComplete(solved, steps, System.currentTimeMillis() - start);
    }

    public String prettyPrint() {
        StringBuffer buf = new StringBuffer();
        for (int row = 0; row < getNumberOfRows(); row++) {
            Tray tray = createTrayFromRow(row);
            buf.append(tray.prettyPrint()).append("\n");
        }
        return buf.toString();
    }

    public int getNumberOfRows() {
        return rows.length;
    }

    public int getRowLength() {
        return getNumberOfColumns();
    }

    public int getNumberOfColumns() {
        return columns.length;
    }

    public int getColumnLength() {
        return getNumberOfRows();
    }

    public int addRow(Line newLine) {
        if (newLine.actualSize() > getRowLength()) throw new IndexOutOfBoundsException("Total size of row is too big.");

        int newRow = -1;
        for (int i = 0; i < getNumberOfRows() && newRow == -1; i++)
            if (rows[i] == null)
                newRow = i;

        if (newRow == -1)
            throw new IndexOutOfBoundsException("Too many rows added.");

        rows[newRow] = newLine;
//        addRowToProcess(newRow);
//        rowTrays[newRow] = new TraySetCreator(newLine, getRowLength()).traySet();
//        listener.rowAdded(newLine, newRow, rowTrays[newRow]);        
        return newRow;
    }

    public int addColumn(Line newLine) {
        if (newLine.actualSize() > getColumnLength()) throw new IndexOutOfBoundsException("Total size of column is too big.");

        int newCol = -1;
        for (int i = 0; i < getNumberOfColumns() && newCol == -1; i++)
            if (columns[i] == null)
                newCol = i;

        if (newCol == -1)
            throw new IndexOutOfBoundsException("Too many columns added.");

        columns[newCol] = newLine;
//        addColumnToProcess(newCol);
//        colTrays[newCol] = new TraySetCreator(newLine, getColumnLength()).traySet();
//        listener.columnAdded(newLine, newCol, colTrays[newCol]);        
        return newCol;
    }

    protected Tray createTrayFromRow(int row) {
        Tray tray = new Tray(board[row]);
        return tray;
    }

    protected Tray createTrayFromColumn(int col) {
        Tray tray = new Tray(getColumnLength());
        for (int i = 0; i < getNumberOfRows(); i++)
            tray.set(i, board[i][col]);
        return tray;
    }

    protected void applyTrayToRow(int row, Tray tray) {
        for (int i = 0; i < tray.size(); i++) {
            CellValue currentValue = board[row][i];
            if ((tray.at(i) != currentValue) &&
                ((tray.at(i) == CellValue.EMPTY) 
                    || (tray.at(i) == CellValue.FILLED) 
//                    || (currentValue == null)
                    )) {
                board[row][i] = tray.at(i);
                addColumnToProcess(i);
            }
        }
    }

    protected void applyTrayToCol(int col, Tray tray) {
        for (int i = 0; i < tray.size(); i++) {
            CellValue currentValue = board[i][col];
            if ((tray.at(i) != currentValue) &&
                    ((tray.at(i) == CellValue.EMPTY) 
                        || (tray.at(i) == CellValue.FILLED) 
//                        || (currentValue == null)
                    )) {
                board[i][col] = tray.at(i);
                addRowToProcess(i);
            }
        }
    }

    protected void addRowToProcess(int i) {
        if (!rowToProcess.contains(i))
            rowToProcess.add(i);
    }

    protected void addColumnToProcess(int i) {
        if (!colToProcess.contains(i))
            colToProcess.add(i);
    }
}