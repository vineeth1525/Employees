package com.cgg.data;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import com.cgg.data.model.Employee;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
//import static io.restassured.response.;


public class StepDefination {

	private RequestSpecification request;
	private Response response;
	private ValidatableResponse json;
	 private static final String BASE_PATH = "http://localhost:8080/employee/addEmployee";
	 
	@Given("employee logins")
	public void employee_logins() {
	
		
		System.out.println("employee logged in succefully");
	}

	

	


	@When("sending my details name <employeeName> id <Id> dept <dept> salary<salary>$")
	public void sending_my_details_name_employee_name_id_id_dept_dept_salary_salary() {
		Employee employee = new Employee("ram", 5,"eee",1000l);
		request= given().contentType(ContentType.JSON).body(employee);
		response = request.when().post(BASE_PATH);
		
//		assertEquals(employee.getEmployeeName(), "ram");
	}
	

		@Then("employee details are printed")
		public void employee_details_are_printed() {
			response.prettyPrint();
		
		}
}//restauserd serinity
