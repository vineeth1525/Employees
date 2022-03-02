package com.cgg.data.service;

import java.util.List;

import com.cgg.data.exception.ServiceException;
import com.cgg.data.model.Employee;

public interface EmployeeService {

	public Employee addEmployee(Employee employee) throws ServiceException;
	public List<Employee> employeeList() throws    ServiceException;
	public Employee getEmployeeById(int id) throws ServiceException;
	public String deleteEmployeeById(int id) throws  ServiceException;
	public String healthCheck(int id);
	
}
