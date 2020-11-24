package com.paypal.bfs.test.employeeserv.impl;

import com.paypal.bfs.test.employeeserv.api.model.Employee;

public class EmployeeValidator {

    public static void validate(Employee employee) throws Exception {

        v("first_name", employee.getFirstName());
        v("last_name", employee.getLastName());
        v("data_of_birth", employee.getDateOfBirth());
        v("address", employee.getAddress().getLine1());

    }

    private static void v(String field, Object data) throws Exception {

        if (data == null) {
            throw new Exception("Invalid data - NULL value : " + field);
        }

        if (data.toString().equalsIgnoreCase("")) {
            throw new Exception("Eempty  " + field);
        }

    }

    public static boolean checkIfExist(EmployeeService es, Employee employee) {

        try {
            es.queryEmp("first_name='" + employee.getFirstName() + "' AND last_name='" + employee.getLastName() + "'");

            return true;
        } catch (Exception ex) {
        }

        return false;
    }
}
