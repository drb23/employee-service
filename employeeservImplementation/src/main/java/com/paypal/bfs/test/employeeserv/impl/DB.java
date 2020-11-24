package com.paypal.bfs.test.employeeserv.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class DB {

    private Connection connection;

    @PostConstruct
    public void init() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:");
        DBConfig.createEmployeeTable(connection);
    }

    public Connection getConnection() {
        return this.connection;
    }

}
