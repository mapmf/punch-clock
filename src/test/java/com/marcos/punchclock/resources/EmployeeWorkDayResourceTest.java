package com.marcos.punchclock.resources;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.marcos.punchclock.dto.EmployeeWorkMonthDTO;
import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.security.UserSecurity;
import com.marcos.punchclock.services.EmployeeService;
import com.marcos.punchclock.services.EmployeeWorkDayService;
import com.marcos.punchclock.services.UserService;
import com.marcos.punchclock.util.EmployeeTestUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeWorkDayResourceTest {

	@Autowired
	private TestRestTemplate tesRestTemplate;

	@MockBean
	private UserService userService;

	@MockBean
	private EmployeeWorkDayService employeeWorkDayService;

	@MockBean
	private EmployeeService employeeService;

	@Test
	public void it_should_get_current_work_day() throws Exception {

		Employee employee = mockAuthenticatedEmployee();

		mockEmployeeWorkDay(employee);

		ResponseEntity<EmployeeWorkDay> result = tesRestTemplate.withBasicAuth("12345678910", "123")
				.getForEntity("/employee-work-days", null, EmployeeWorkDay.class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
	}

	@Test
	public void it_should_forbidden_to_get_current_work_day_of_another_employee() throws Exception {

		mockAuthenticatedEmployee();

		Employee anotherEmployee = EmployeeTestUtil.createEmployee();

		mockEmployeeWorkDay(anotherEmployee);

		ResponseEntity<EmployeeWorkDay> result = tesRestTemplate.withBasicAuth("12345678910", "123")
				.getForEntity("/employee-work-days/" + anotherEmployee.getId(), null, EmployeeWorkDay.class);

		assertEquals(HttpStatus.FORBIDDEN.value(), result.getStatusCode().value());
	}

	@Test
	public void it_should_get_current_work_day_of_another_employee_as_admin() throws Exception {

		mockAuthenticatedAdmin();

		Employee anotherEmployee = EmployeeTestUtil.createEmployee();

		mockEmployeeWorkDay(anotherEmployee);

		ResponseEntity<EmployeeWorkDay> result = tesRestTemplate.withBasicAuth("admin", "admin")
				.getForEntity("/employee-work-days/" + anotherEmployee.getId(), null, EmployeeWorkDay.class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
	}

	@Test
	public void it_should_get_current_work_month() throws Exception {

		Employee employee = mockAuthenticatedEmployee();

		mockEmployeeWorkDay(employee);

		ResponseEntity<EmployeeWorkMonthDTO> result = tesRestTemplate.withBasicAuth("12345678910", "123")
				.getForEntity("/employee-work-days/get-by-month", null, EmployeeWorkMonthDTO.class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
	}

	@Test
	public void it_should_forbidden_to_get_current_work_month_of_another_employee() throws Exception {

		mockAuthenticatedEmployee();

		Employee anotherEmployee = EmployeeTestUtil.createEmployee();

		mockEmployeeWorkDay(anotherEmployee);

		ResponseEntity<EmployeeWorkMonthDTO> result = tesRestTemplate.withBasicAuth("12345678910", "123").getForEntity(
				"/employee-work-days/get-by-month/" + anotherEmployee.getId(), null, EmployeeWorkMonthDTO.class);

		assertEquals(HttpStatus.FORBIDDEN.value(), result.getStatusCode().value());
	}

	@Test
	public void it_should_get_current_work_month_of_another_employee_as_admin() throws Exception {

		mockAuthenticatedAdmin();

		Employee anotherEmployee = EmployeeTestUtil.createEmployee();

		mockEmployeeWorkDay(anotherEmployee);

		ResponseEntity<EmployeeWorkMonthDTO> result = tesRestTemplate.withBasicAuth("admin", "admin").getForEntity(
				"/employee-work-days/get-by-month/" + anotherEmployee.getId(), null, EmployeeWorkMonthDTO.class);

		assertEquals(HttpStatus.OK.value(), result.getStatusCode().value());
	}

	private EmployeeWorkDay mockEmployeeWorkDay(Employee employee) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());

		EmployeeWorkDay workDay = new EmployeeWorkDay(null, employee, calendar.getTime());

		Mockito.when(employeeWorkDayService.getByEmployeeAndDate(employee, calendar.getTime())).thenReturn(workDay);

		return workDay;

	}

	private Employee mockAuthenticatedEmployee() {

		Employee admin = EmployeeTestUtil.createEmployee("12345678910", "123");

		UserSecurity userSecurity = new UserSecurity(admin.getId(), admin.getPassword(), admin.getProfiles());

		Mockito.when(employeeService.getById(admin.getId())).thenReturn(admin);
		Mockito.when(userService.authenticated()).thenReturn(userSecurity);

		return admin;

	}

	private Employee mockAuthenticatedAdmin() {

		Employee admin = EmployeeTestUtil.createAdminEmployee();
		
		UserSecurity userSecurity = new UserSecurity(admin.getId(), admin.getPassword(), admin.getProfiles());

		Mockito.when(employeeService.getById(admin.getId())).thenReturn(admin);
		Mockito.when(userService.authenticated()).thenReturn(userSecurity);

		return admin;

	}

}
