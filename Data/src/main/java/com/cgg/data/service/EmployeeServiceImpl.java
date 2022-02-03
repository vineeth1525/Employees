package com.cgg.data.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgg.data.controller.EmployeeController;
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

	Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Override
	public EmployeeDto addEmployee(EmployeeDto employee) throws ServiceException {
		try {
			Employee saveEmployee = employeeRepository.save(employeeMapper.fromEmployeeDto(employee));
			return employeeMapper.toEmployeeDto(saveEmployee);
		} catch (Exception e) {
			logger.error("couldnot add employee");
			throw new ServiceException("couldnot add employee" + e);
		}
	}

	@Override
	public List<EmployeeDto> employeeList() throws   ServiceException {
		
			List<Employee> employeevo = employeeRepository.findAll();
			try{if(!employeevo.isEmpty()) {
				
				return employeeMapper.toEmployeeListDto(employeevo);
			}
			else throw new NoRecordFoundException("list is empty");
			}
			catch(NoRecordFoundException e) {
				logger.error(e.getMessage());
				throw new ServiceException(e.getMessage());
			}
			
	}

	@Override
	public String deleteEmployeeById(int id) throws  ServiceException {
		try {if (employeeRepository.existsById(id)) {
			employeeRepository.deleteById(id);
		} else

			throw new EmployeeNotFoundException("id doesnt exists");}
		catch(EmployeeNotFoundException e) {
			throw new ServiceException(e);
		}
		return "employee deleted";
	}

	@Override
	public EmployeeDto getEmployeeById(int id) throws ServiceException {
		try {
			Employee employeeById = employeeRepository.findById(id)
					.orElseThrow(() -> new EmployeeNotFoundException("employee doesnt exists"));
			return employeeMapper.toEmployeeDto(employeeById);
		} catch (EmployeeNotFoundException e) {
			throw new ServiceException(e.getMessage());
		}
	}

	@Override
	public String healthCheck(int id) {
		Optional<Employee> emp = employeeRepository.findById(id);
		EmployeeDto employee1 =new EmployeeDto();
		try {
			
		employee1.setEmployeeName("musk");
		employee1.setDept("EEE");
		employee1.setId(4);
		employee1.setSalary(33000);
		if(!emp.isPresent())
		logger.info("health check successfull");
		return "Health check successfull";
		}catch(Exception e) {
			logger.error("Exception in health check");
			return "Not success";
		}
		
	}

}
