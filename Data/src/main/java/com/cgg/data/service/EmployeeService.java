package com.cgg.data.service;

import java.util.List;

import com.cgg.data.exception.ServiceException;
import com.cgg.data.model.EmployeeDto;

public interface EmployeeService {

	public EmployeeDto addEmployee(EmployeeDto employee) throws ServiceException;
	public List<EmployeeDto> employeeList() throws    ServiceException;
	public EmployeeDto getEmployeeById(int id) throws ServiceException;
	public String deleteEmployeeById(int id) throws  ServiceException;
	public String healthCheck(int id);
	
}
