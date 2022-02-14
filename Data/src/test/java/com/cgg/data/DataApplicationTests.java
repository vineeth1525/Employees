package com.cgg.data;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.testng.Assert;

import com.cgg.data.controller.EmployeeController;
import com.cgg.data.model.EmployeeDto;
import com.cgg.data.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
//@AutoConfigureMockMvc
class DataApplicationTests {

	
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	
	@Mock
	private EmployeeService employeeService;

	@InjectMocks
	private EmployeeController employeeController;

	@BeforeAll
private void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
}

	@Test
	 void addEmployeeTest() throws Exception {
		EmployeeDto employeeDto = new EmployeeDto("ram", "eee", 1200l, 5);
		String jsonRequest = objectMapper.writeValueAsString(employeeDto);
		Mockito.when(employeeService.addEmployee(employeeDto)).thenReturn(employeeDto);
		mockMvc.perform(MockMvcRequestBuilders.post("/employee/addEmployee").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}

	@Test
	 void getEmployeeByIdTest() throws Exception {
		EmployeeDto employeeDto = new EmployeeDto("ram", "eee", 1200l, 5);
		Mockito.when(employeeService.getEmployeeById(Mockito.anyInt())).thenReturn(employeeDto);
		mockMvc.perform(get("/employee/getEmployeeById/5")).andExpect(jsonPath("employeeName", is("ram")));
	}

	@Test
	 void getEmployees() throws Exception {
		List<EmployeeDto> employeeDto=new ArrayList<EmployeeDto>();
		EmployeeDto empDto1 = new EmployeeDto("sham", "ece", 8800l, 7);
		EmployeeDto empDto2 = new EmployeeDto("ram", "eee", 1200l, 4);
		
		employeeDto.add(empDto2);
		employeeDto.add(empDto1);
		Mockito.when(employeeService.employeeList()).thenReturn(employeeDto);
	mockMvc.perform(get("/employee/getAllEmployee")).andExpect(status().isOk()).andReturn();
	Assert.assertEquals(employeeDto.size(), 2);
		
	}
	@Test
	 void deleteEmployeeById() throws Exception {
		EmployeeDto empDto2 = new EmployeeDto("ram", "eee", 1200l, 4);
		
		Mockito.when(employeeService.deleteEmployeeById(empDto2.getId())).thenReturn("employee deleted");
		mockMvc.perform(delete("/employee/deleteEmployeeById/4")).andExpect(status().isAccepted());
		
	}
	
}
