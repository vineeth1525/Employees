package com.cgg.data.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cgg.data.eo.EmployeeEO;
import com.cgg.data.exception.ServiceException;
import com.cgg.data.model.EmployeeDto;

@Component
public class EmployeeBOImpl implements EmployeeBO {
	Logger logger = LoggerFactory.getLogger(EmployeeBOImpl.class);

	@Autowired
	EmployeeEO employeeEO;
	
	@Override
	public EmployeeDto addEmployee(EmployeeDto employee) throws ServiceException {
		logger.info("In addEmployee BO ");

		return employeeEO.addEmployee(employee);
	}

	@Override
	public List<EmployeeDto> employeeList() throws ServiceException {
		logger.info("In employeeList BO ");
		return employeeEO.employeeList();
	}

	@Override
	public EmployeeDto getEmployeeById(int id) throws ServiceException {
		logger.info("In getEmployeeById BO ");
		return employeeEO.getEmployeeById(id);
	}

	@Override
	public void deleteEmployeeById(int id) throws ServiceException {
		logger.info("In deleteEmployeeById BO ");
		employeeEO.deleteEmployeeById(id);
	}

	@Override
	public String healthCheck(int id) {
		return null;
	}

}
