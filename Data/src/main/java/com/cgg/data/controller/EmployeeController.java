package com.cgg.data.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.executable.ValidateOnExecution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cgg.data.exception.ServiceException;
import com.cgg.data.model.EmployeeDto;
import com.cgg.data.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;
	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@PostMapping("/addEmployee")

	public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employee) throws ServiceException {
		logger.info("added employee");
		return ResponseEntity.status(HttpStatus.CREATED).body(employeeService.addEmployee(employee));

	}

	@GetMapping("/getAllEmployee")
	public ResponseEntity<List<EmployeeDto>> getEmployeeList() throws ServiceException {
		logger.info("In getAll method");
		List<EmployeeDto> list = employeeService.employeeList();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		} else
			return ResponseEntity.ok(list);

	}

	@GetMapping("/getEmployeeById/{id}")
	public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable int id) throws ServiceException {
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.getEmployeeById(id));
	}

	@DeleteMapping("/deleteEmployeeById/{id}")
	public ResponseEntity<String> deleteEmployeeById(@PathVariable int id) throws ServiceException {
		logger.info("Inside deleteEmployee method");
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeService.deleteEmployeeById(id));

	}
	@GetMapping("/healthCheck/{id}")
	public ResponseEntity<String> healthCheck(@PathVariable int id){
		return ResponseEntity.status(HttpStatus.OK).body(employeeService.healthCheck(id));
	}
}
