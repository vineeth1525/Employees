package com.cgg.data.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgg.data.bo.EmployeeBO;
import com.cgg.data.eo.EmployeeEO;
import com.cgg.data.exception.EmployeeNotFoundException;
import com.cgg.data.exception.NoRecordFoundException;
import com.cgg.data.exception.ServiceException;
import com.cgg.data.mapper.EmployeeMapper;
import com.cgg.data.model.Employee;
import com.cgg.data.model.EmployeeDto;
import com.cgg.data.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeMapper employeeMapper;
	
	@Autowired
	EmployeeBO employeeBO;
	
	@Autowired
	EmployeeEO employeeEO;
	
	

	Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Override
	public Employee addEmployee(Employee employee) throws ServiceException {
		try {
			EmployeeDto saveEmployee = employeeBO.addEmployee(employeeMapper.toEmployeeDto(employee));
			return employeeMapper.fromEmployeeDto(saveEmployee);
		} catch (Exception e) {
			logger.error("couldnot add employee");
			throw new ServiceException("couldnot add employee" + e);
		}
	}

	@Override
	public List<Employee> employeeList() throws ServiceException {

		List<EmployeeDto> employeevo = employeeBO.employeeList();
		try {
			if (employeevo.isEmpty()) {
				logger.info("employee list fetched");
				throw new NoRecordFoundException("list is empty");
				
			} else
				return employeeMapper.fromEmployeeListDto(employeevo);
		} catch (NoRecordFoundException e) {
			logger.info(e.getMessage());
			throw new ServiceException(e.getMessage());
		}

	}

	@Override
	public String deleteEmployeeById(int id) throws ServiceException {
		try {
			if (employeeRepository.existsById(id)) {
				logger.info("Inside delete employee service");
				employeeRepository.deleteById(id);
			} else

				throw new EmployeeNotFoundException("id doesnt exists");
		} catch (EmployeeNotFoundException e) {
			throw new ServiceException(e);
		}
		return "employee deleted";
	}

	@Override
	public Employee getEmployeeById(int id) throws ServiceException {
		try {
			logger.info("Inside get employee by id service");
			EmployeeDto employeeById = employeeRepository.findById(id)
					.orElseThrow(() -> new EmployeeNotFoundException("employee doesnt exists"));
			return employeeMapper.fromEmployeeDto(employeeById);
		} catch (EmployeeNotFoundException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String healthCheck(int id) {
		Optional<EmployeeDto> emp = employeeRepository.findById(id);
		EmployeeDto employee1 = new EmployeeDto();
		try {

			employee1.setEmployeeName("musk");
			employee1.setDept("EEE");
			employee1.setId(4);
			employee1.setSalary(33000);
			if (!emp.isPresent())
				logger.info("health check successfull");
			return "Health check successfull";
		} catch (Exception e) {
			logger.error("Exception in health check");
			return "Not success";
		}

	}

}
