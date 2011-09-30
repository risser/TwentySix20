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
* Copyright (c) 2007 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Jun 25, 2007
*/
package com.twentysix20.lolcode.exception;


public class DuplicateIdentifierException extends RuntimeException {

    public DuplicateIdentifierException(String identifier) {
        super("Identifier already exists: "+identifier);
    }
}
