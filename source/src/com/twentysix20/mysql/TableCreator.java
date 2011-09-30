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
* Created on Jun 20, 2005
*/
package com.twentysix20.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Peter Risser (TPNR007)
 *
 */
public class TableCreator {

    public static void main(String[] args) throws Exception {
        //load the Driver class
        Class.forName("com.mysql.jdbc.Driver");        

        String url = "jdbc:mysql://localhost:3306/coffeebreak";
        Connection con = DriverManager.getConnection(url, "risser", "hogmyleg");
        Statement stmt = con.createStatement();

        String updateString = "CREATE TABLE COFFEES (CoffeeName varchar(32), SupplierID integer, Price float, Sales integer, Total integer)";
        stmt.executeUpdate(updateString);
        System.out.println("Done.");
    }
}
