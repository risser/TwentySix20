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
* Copyright (c) 2006 Ohio Farmers Insurance Company
* All rights reserved.
*
* Created on Oct 7, 2006
*/
package com.twentysix20.mtg.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.twentysix20.mtg.data.card.*;

public class OracleDAO {

    public void save(StandardCard card) throws ClassNotFoundException, SQLException {
        //load the Driver class
        Class.forName("com.mysql.jdbc.Driver");        
        
        String url = "jdbc:mysql://localhost:3306/mtg01";
        Connection con = DriverManager.getConnection(url, "risser", "hogmyleg");
        Statement stmt = con.createStatement();
//        updateString = "CREATE TABLE COFFEES (CoffeeName varchar(32), SupplierID integer, Price float, Sales integer, Total integer)";
        stmt.executeUpdate("INSERT INTO ORACLE VALUES ('Colombian', 101, 7.99, 0, 0)");
//        ResultSet rs = stmt.executeQuery("select CoffeeName, Price from COFFEES");
//        while (rs.next()) {
//            String name = rs.getString("CoffeeName");
//            float price = rs.getFloat("Price");
//            System.out.println(name + " is $" + price + " per pound");
//        }
    }
}
