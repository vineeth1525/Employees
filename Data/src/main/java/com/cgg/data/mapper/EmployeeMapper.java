package com.cgg.data.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.cgg.data.model.Employee;
import com.cgg.data.model.EmployeeDto;

@Mapper(componentModel = "spring")

public interface EmployeeMapper {

	
	EmployeeDto toEmployeeDto(Employee employee);

	Employee fromEmployeeDto(EmployeeDto employeeDto);

	List<EmployeeDto> toEmployeeListDto(List<Employee> employee);

	List<Employee> fromEmployeeListDto(List<EmployeeDto> employeeDto);
}
