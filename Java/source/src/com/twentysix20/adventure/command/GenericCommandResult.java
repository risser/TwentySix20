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
* Copyright (c) 2005 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Nov 23, 2005
*/
package com.twentysix20.adventure.command;

public class GenericCommandResult implements CommandResult {
    private int value;
    
    public GenericCommandResult(int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
}
