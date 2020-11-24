package com.paypal.bfs.test.employeeserv.impl;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.paypal.bfs.test.employeeserv.api.EmployeeResource;
import com.paypal.bfs.test.employeeserv.api.model.Employee;

/**
 * Implementation class for employee resource.
 */
@RestController
public class EmployeeResourceImpl implements EmployeeResource {

	private Logger log = LoggerFactory.getLogger("EmployeeResourceImpl");

	@Autowired
	private EmployeeService es;

	@Override
	public ResponseEntity<Employee> employeeGetById(String id) throws Exception {
		return new ResponseEntity<>(es.getEmployee(id), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Employee> addEmployee(Employee employee) throws Exception {
		Employee emp = es.addEmployee(employee);
		return new ResponseEntity<>(emp, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity<Object> exceptionHandler(Exception ex) {
		log.error("Error: ", ex.getMessage());

		Map<String, String> err = new HashMap<String, String>();
		err.put("status", "error");
		err.put("message", ex.getMessage());

		return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
	}

}
