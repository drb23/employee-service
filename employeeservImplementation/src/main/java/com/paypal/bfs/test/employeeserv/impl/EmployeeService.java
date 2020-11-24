package com.paypal.bfs.test.employeeserv.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.paypal.bfs.test.employeeserv.api.model.Address;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

@Component
public class EmployeeService {

    private Logger log = LoggerFactory.getLogger("EmployeeService");

    @Autowired
    private DB db;

    public Employee addEmployee(Employee employee) throws Exception {

        EmployeeValidator.validate(employee);

        boolean exist = EmployeeValidator.checkIfExist(this, employee);

        if (exist) {
            throw new Exception("Employee already exist");
        }

        log.info("Adding employee on database");

        String sql = "INSERT INTO employee(" //
                + "first_name,last_name,date_of_birth," //
                + "address_line1,address_line2,city,state," //
                + "country,zip_code) VALUES(?,?,?,?,?,?,?,?,?)";

        try (PreparedStatement stmt = db.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            this.fillData(employee, stmt);
            int affectedRows = stmt.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Employee creation failed");
            }

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                employee = this.getEmployee(rs.getString(1));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return employee;
    }

    private void fillData(Employee employee, PreparedStatement stmt) throws SQLException {

        stmt.setString(1, employee.getFirstName());
        stmt.setString(2, employee.getLastName());

        stmt.setDate(3, new java.sql.Date(employee.getDateOfBirth().getTime()));

        Address add = employee.getAddress();

        stmt.setString(4, add.getLine1());
        stmt.setString(5, add.getLine2());
        stmt.setString(6, add.getCity());
        stmt.setString(7, add.getState());
        stmt.setString(8, add.getCountry());
        stmt.setString(9, add.getZipCode());
    }

    public Employee getEmployee(String id) throws Exception {
        return queryEmp("id=" + id);
    }

    public Employee queryEmp(String where) throws Exception {

        try (Statement stmt = db.getConnection().createStatement()) {

            String sql = "select * from employee WHERE " + where;

            log.info("Sql: {}", sql);

            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next() == false) {
                throw new Exception("Employee not found " + where);
            }

            Employee employee = populateEmpData(rs);
            rs.close();

            return employee;

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    private Employee populateEmpData(ResultSet rs) throws SQLException {

        Employee employee = new Employee();

        employee.setId(rs.getInt("id"));
        employee.setFirstName(rs.getString("first_name"));
        employee.setLastName(rs.getString("last_name"));
        employee.setDateOfBirth(rs.getDate("date_of_birth"));
        employee.setAddress(populateAddress(rs));

        return employee;
    }

    private Address populateAddress(ResultSet rs) throws SQLException {
        Address add = new Address();
        add.setLine1(rs.getString("address_line1"));
        add.setLine2(rs.getString("address_line2"));
        add.setCity(rs.getString("city"));
        add.setState(rs.getString("state"));
        add.setCountry(rs.getString("country"));
        add.setZipCode(rs.getString("zip_code"));
        return add;
    }

}
