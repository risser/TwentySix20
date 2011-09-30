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
* Created on Dec 5, 2008
*/
package com.twentysix20.oekaki;

import java.util.HashSet;
import java.util.Set;

public class TraySet {
    private Set<Tray> set = new HashSet<Tray>();

    public TraySet() {}

    public void add(Tray tray) {
        set.add(tray);
    }

    public Tray collate(Tray base) {
        Set<Tray> fails = new HashSet<Tray>();
        Tray result = new Tray(base.size());
        for (Tray tray : set) {
            if (tray.matches(base))
                result.merge(tray);
            else 
                fails.add(tray);
        }
        set.removeAll(fails);
        if (set.size() == 0) throw new IllegalStateException("Whoops! The tray you've asked to collate against doesn't match existing records.  Something may be wrong with your puzzle setup.");
        return result;
    }

    public int size() {
        return set.size();
    }

    public boolean isSolved() {
        return (size() == 1) && set.iterator().next().isDefinitive();
    }
}