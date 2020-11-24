package com.paypal.bfs.test.employeeserv.impl;

import java.sql.Connection;
import java.sql.Statement;

public class DBConfig {

    public static void createEmployeeTable(Connection connection) {

        try (Statement stmt = connection.createStatement()) {

            StringBuilder sb = new StringBuilder();

            sb.append("CREATE TABLE employee (");
            sb.append("id INT auto_increment,");
            sb.append("first_name varchar(50) NOT NULL,");
            sb.append("last_name varchar(50) NOT NULL,");
            sb.append("date_of_birth datetime NOT NULL,");
            sb.append("address_line1 varchar(50) NOT NULL,");
            sb.append("address_line2 varchar(50),");
            sb.append("city varchar(50),");
            sb.append("state varchar(50),");
            sb.append("country varchar(50),");
            sb.append("zip_code varchar(10)");
            sb.append(")");

            stmt.executeUpdate(sb.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
