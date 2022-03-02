package com.cgg.data.eo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgg.data.exception.ServiceException;
import com.cgg.data.model.EmployeeDto;
import com.cgg.data.repository.EmployeeRepository;


@Component
public class EmployeeEOimpl implements EmployeeEO {
	
	Logger logger = LoggerFactory.getLogger(EmployeeEOimpl.class);
	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public EmployeeDto addEmployee(EmployeeDto employee) throws ServiceException {
		logger.info("In addEmployee EO ");
		return employeeRepository.save(employee);
	}

	@Override
	public List<EmployeeDto> employeeList() throws ServiceException {
		logger.info("In employeeList EO ");
		return employeeRepository.findAll();
	}

	@Override
	public EmployeeDto getEmployeeById(int id) throws ServiceException {
		logger.info("In getEmployeeById EO ");
		return employeeRepository.getById(id);
	}

	@Override
	public void deleteEmployeeById(int id) throws ServiceException {
		logger.info("In deleteEmployeeById EO ");

		employeeRepository.getById(id);
				
	}

	@Override
	public String healthCheck(int id) {
		return null;
	}

}
