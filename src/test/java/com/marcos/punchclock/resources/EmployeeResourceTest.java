package com.marcos.punchclock.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marcos.punchclock.dto.EmployeeNewDTO;
import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.security.UserSecurity;
import com.marcos.punchclock.services.EmployeeService;
import com.marcos.punchclock.services.UserService;
import com.marcos.punchclock.util.EmployeeTestUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeResourceTest {

	@Autowired
	private TestRestTemplate tesRestTemplate;

	@MockBean
	private UserService userService;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void it_should_list_employees() throws Exception {

		mockAuthenticatedAdmin();
		
		ResponseEntity<List<Employee>> result = tesRestTemplate.withBasicAuth("admin", "admin").getForEntity("/employees",
				null, List.class);
		
		assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
	}
	
	@Test
	public void it_should_forbidden_to_list_employees() throws Exception {

		mockAuthenticatedEmployee();
		
		ResponseEntity<List<Employee>> result = tesRestTemplate.withBasicAuth("12345678910", "123").getForEntity("/employees",
				null, List.class);
		
		assertEquals(HttpStatus.FORBIDDEN.value(), result.getStatusCode().value());
	}
	
	@Test
	public void it_should_forbidden_to_add_new_employee() throws Exception {

		mockAuthenticatedEmployee();
		
		EmployeeNewDTO dto = mockEmployeeDTO();
		
		ResponseEntity<Void> result = tesRestTemplate.withBasicAuth("12345678910", "123").postForEntity("/employees",
				dto, Void.class);
		
		assertEquals(HttpStatus.FORBIDDEN.value(), result.getStatusCode().value());
	}

	@Test
	public void it_should_add_new_employee() throws Exception {

		mockAuthenticatedAdmin();
		
		EmployeeNewDTO dto = mockEmployeeDTO();
		
		ResponseEntity<Void> result = tesRestTemplate.withBasicAuth("admin", "admin").postForEntity("/employees",
				dto, Void.class);
		
		assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
	}

	private EmployeeNewDTO mockEmployeeDTO() {

		String password = "123";
		
		Employee employee = EmployeeTestUtil.createEmployee("12345678910", password);
		
		EmployeeNewDTO dto = new EmployeeNewDTO(employee);
		dto.setPassword(password);

		Mockito.when(employeeService.getById(employee.getId())).thenReturn(employee);
		
		Mockito.when(employeeService.fromDTO(dto)).thenReturn(employee);
		
		Mockito.when(employeeService.insert(employee)).thenReturn(employee);
		
		return dto;
	}
	
	private Employee mockAuthenticatedEmployee() {
		
		Employee employee = EmployeeTestUtil.createEmployee("12345678910", "123");

		UserSecurity userSecurity = new UserSecurity(employee.getId(), employee.getPassword(), employee.getProfiles());
		
		Mockito.when(employeeService.getById(employee.getId())).thenReturn(employee);
		Mockito.when(userService.authenticated()).thenReturn(userSecurity);
		
		return employee;

	}
	
	private Employee mockAuthenticatedAdmin() {
		
		Employee admin = EmployeeTestUtil.createAdminEmployee();

		UserSecurity userSecurity = new UserSecurity(admin.getId(), admin.getPassword(), admin.getProfiles());
		
		Mockito.when(employeeService.getById(admin.getId())).thenReturn(admin);
		Mockito.when(userService.authenticated()).thenReturn(userSecurity);
		
		return admin;

	}
	
}
