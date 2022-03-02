package com.cgg.data;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testng.Assert;

import com.cgg.data.model.Employee;
import com.cgg.data.service.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureMockMvc
class DataApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	ObjectMapper objectMapper = new ObjectMapper();
	
//	@Mock
	@MockBean
	private EmployeeServiceImpl employeeService;
//
//	@InjectMocks
//	private EmployeeController employeeController;
//
//	@BeforeAll
//private void setUp() {
//		mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
//}

	@Test
	 void addEmployeeTest() throws Exception {
		Employee employee = new Employee("ram", 5,"eee",1000l);
		String jsonRequest = objectMapper.writeValueAsString(employee);
		Mockito.when(employeeService.addEmployee(employee)).thenReturn(employee);
		mockMvc.perform(MockMvcRequestBuilders.post("/employee/addEmployee").content(jsonRequest)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		
	}

	@Test
	 void getEmployeeByIdTest() throws Exception {
		Employee employeeDto = new Employee("ram", 5,"eee",1000l);
		Mockito.when(employeeService.getEmployeeById(Mockito.anyInt())).thenReturn(employeeDto);
		mockMvc.perform(get("/employee/getEmployeeById/5")).andExpect(jsonPath("employeeName", is("ram")));
	}

	@Test
	 void getEmployees() throws Exception {
		List<Employee> employeeDto=new ArrayList<Employee>();
		Employee emp1 = new Employee("ram", 5,"eee",1000l);
		Employee emp2 = new Employee("sham",9 ,"eee",1000l);
		
		employeeDto.add(emp2);
		employeeDto.add(emp1);
		Mockito.when(employeeService.employeeList()).thenReturn(employeeDto);
	mockMvc.perform(get("/employee/getAllEmployee")).andExpect(status().isOk()).andReturn();
	Assert.assertEquals(employeeDto.size(), 2);
		
	}
	
	@Test
	 void getNoEmployees() throws Exception {
		List<Employee> employeeDto=new ArrayList<Employee>();
		
		Mockito.when(employeeService.employeeList()).thenReturn(employeeDto);
	mockMvc.perform(get("/employee/getAllEmployee")).andExpect(status().isNotFound()).andReturn();
	Assert.assertEquals(employeeDto.size(), 0);
		
	}
	
	@Test
	 void deleteEmployeeById() throws Exception {Employee emp = new Employee("ram", 5,"eee",1000l);
		Mockito.when(employeeService.deleteEmployeeById(emp.getId())).thenReturn("employee deleted");
		mockMvc.perform(delete("/employee/deleteEmployeeById/4")).andExpect(status().isAccepted());
		
	}
	
}
