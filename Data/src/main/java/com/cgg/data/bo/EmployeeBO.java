package com.cgg.data.bo;

import java.util.List;

import com.cgg.data.exception.ServiceException;
import com.cgg.data.model.EmployeeDto;

public interface EmployeeBO {

	public EmployeeDto addEmployee(EmployeeDto employee) throws ServiceException;
	public List<EmployeeDto> employeeList() throws    ServiceException;
	public EmployeeDto getEmployeeById(int id) throws ServiceException;
	public void deleteEmployeeById(int id) throws  ServiceException;
	public String healthCheck(int id);
}
