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
* Created on Dec 6, 2008
*/
package com.twentysix20.oekaki;


public class TraySetCreator {
    final private TraySet results = new TraySet();
    final private Line baseLine;
    final private int[] hold;
    final private int trayLength;
    final private Tray baseTray;

    public TraySetCreator(Line baseLine, int trayLength) {
        this.baseLine = baseLine;
        this.trayLength = trayLength;
        this.baseTray = null;
        hold = new int[baseLine.size() + 1];
        fill(0, trayLength - baseLine.actualSize());
    }

    public TraySetCreator(Line baseLine, Tray baseTray) {
        this.baseLine = baseLine;
        this.trayLength = baseTray.size();
        this.baseTray = baseTray;
        hold = new int[baseLine.size() + 1];
        fill(0, trayLength - baseLine.actualSize());
    }

    public TraySet traySet() {
        return results;
    }

    private void fill(int index, int remaining) {
        if (index == hold.length - 1) {
            hold[index] = remaining;
            Tray tray = createTray();
            if ((baseTray == null) ||
                    ((baseTray != null) && baseTray.matches(tray)))
                results.add(tray);
            if ((results.size() % 100000 == 0) && (results.size() > 0))
                System.out.println("... "+results.size());
        } else {
            for (int i = 0; i <= remaining; i++) {
                hold[index] = i;
                fill(index+1, remaining - i);
            }
        }
    }

    private Tray createTray() {
        Tray tray = new Tray(trayLength);
        int trayPos = 0;
        for (int i = 0; i < baseLine.size(); i++) {
            for (int j = 0; j < hold[i]; j++)
                tray.set(trayPos++, CellValue.EMPTY);
            if (i != 0)
                tray.set(trayPos++, CellValue.EMPTY); // every space needs 1 added except 1st and last.
            for (int j = 0; j < baseLine.get(i); j++)
                tray.set(trayPos++, CellValue.FILLED);
        }
        for (int j = 0; j < hold[hold.length-1]; j++)
            tray.set(trayPos++, CellValue.EMPTY);

        return tray;
    }
}