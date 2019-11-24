package com.marcos.punchclock.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.security.UserSecurity;
import com.marcos.punchclock.services.EmployeeService;
import com.marcos.punchclock.services.PunchClockService;
import com.marcos.punchclock.services.UserService;
import com.marcos.punchclock.util.EmployeeTestUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class PunchClockResourceTest {

	@Autowired
	private TestRestTemplate tesRestTemplate;

	@MockBean
	private PunchClockService punchClockService;

	@MockBean
	private UserService userService;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void it_should_add_punch_clock() throws Exception {

		mockAuthenticatedEmployee();

		ResponseEntity<Void> result = tesRestTemplate.withBasicAuth("12345678910", "123").postForEntity("/punch-clocks",
				null, Void.class);

		assertEquals(HttpStatus.CREATED.value(), result.getStatusCode().value());
	}
	
	private Employee mockAuthenticatedEmployee() {
		
		Employee employee = EmployeeTestUtil.createEmployee("12345678910", "123");
		
		UserSecurity userSecurity = new UserSecurity(employee.getId(), employee.getPassword(), employee.getProfiles());
		
		Mockito.when(employeeService.getById(employee.getId())).thenReturn(employee);
		Mockito.when(userService.authenticated()).thenReturn(userSecurity);
		
		return employee;
	}
}
