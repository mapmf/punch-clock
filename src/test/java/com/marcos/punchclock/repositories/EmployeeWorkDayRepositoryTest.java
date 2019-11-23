package com.marcos.punchclock.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.util.DateUtil;

@DataJpaTest
public class EmployeeWorkDayRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeWorkDayRepository employeeWorkDayRepository;

	@Test
	public void it_should_save_work_day() {

		EmployeeWorkDay employeeWorkDay = createWorkDay();

		Employee employee = employeeWorkDay.getEmployee();
		Date createdAt = employeeWorkDay.getCreatedAt();

		EmployeeWorkDay persistedEmployeeWorkDay = employeeWorkDayRepository.save(employeeWorkDay);

		assertEquals(employee, persistedEmployeeWorkDay.getEmployee());
		assertEquals(createdAt, persistedEmployeeWorkDay.getCreatedAt());
		assertNotNull(persistedEmployeeWorkDay.getId());

	}

	@Test
	public void it_should_get_current_work_day() {

		EmployeeWorkDay employeeWorkDay = createWorkDay();

		employeeWorkDayRepository.save(employeeWorkDay);

		Employee employee = employeeWorkDay.getEmployee();
		Date createdAt = employeeWorkDay.getCreatedAt();

		Date beginOfDay = DateUtil.getBeginOfDay(createdAt);
		Date endOfDay = DateUtil.getEndOfDay(createdAt);

		EmployeeWorkDay persistedEmployeeWorkDay = employeeWorkDayRepository
				.findByEmployeeAndCreatedAtBetween(employee, beginOfDay, endOfDay).get(0);

		assertEquals(employee, persistedEmployeeWorkDay.getEmployee());
		assertEquals(createdAt, persistedEmployeeWorkDay.getCreatedAt());
		assertNotNull(persistedEmployeeWorkDay.getId());

	}

	private EmployeeWorkDay createWorkDay() {

		Employee employee = new Employee();
		employee.setId("12345678910");
		employee.setName("Marcos");

		employee = employeeRepository.save(employee);

		Date date = new Date();

		EmployeeWorkDay employeeWorkDay = new EmployeeWorkDay(null, employee, date);

		employee.getEmployeeWorkDays().add(employeeWorkDay);

		return employeeWorkDay;
	}

}
