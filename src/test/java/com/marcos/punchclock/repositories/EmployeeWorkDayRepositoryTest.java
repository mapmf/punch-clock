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
import com.marcos.punchclock.util.EmployeeTestUtil;

@DataJpaTest
public class EmployeeWorkDayRepositoryTest {

	@Autowired
	EmployeeRepository employeeRepository;

	@Autowired
	EmployeeWorkDayRepository employeeWorkDayRepository;

	@Test
	public void it_should_save_work_day() {

		Employee expectedEmployee = EmployeeTestUtil.createEmployee();
		
		EmployeeWorkDay expectedEmployeeWorkDay = createWorkDay(expectedEmployee);

		Date expectedCreatedAt = expectedEmployeeWorkDay.getCreatedAt();

		EmployeeWorkDay actualEmployeeWorkDay = employeeWorkDayRepository.save(expectedEmployeeWorkDay);

		assertEquals(expectedEmployee.getId(), actualEmployeeWorkDay.getEmployee().getId());
		assertEquals(expectedCreatedAt, actualEmployeeWorkDay.getCreatedAt());
		assertNotNull(actualEmployeeWorkDay.getId());

	}

	@Test
	public void it_should_get_current_work_day() {

		Employee expectedEmployee = EmployeeTestUtil.createEmployee();
		
		EmployeeWorkDay expectedEmployeeWorkDay = createWorkDay(expectedEmployee);

		Date expectedCreatedAt = expectedEmployeeWorkDay.getCreatedAt();

		Date beginOfDay = DateUtil.getBeginOfDay(expectedCreatedAt);
		Date endOfDay = DateUtil.getEndOfDay(expectedCreatedAt);
		
		employeeWorkDayRepository.save(expectedEmployeeWorkDay);

		EmployeeWorkDay actualEmployeeWorkDay = employeeWorkDayRepository
				.findByEmployeeAndCreatedAtBetween(expectedEmployee, beginOfDay, endOfDay).get(0);

		assertEquals(expectedEmployee.getId(), actualEmployeeWorkDay.getEmployee().getId());
		assertEquals(expectedCreatedAt, actualEmployeeWorkDay.getCreatedAt());
		assertNotNull(actualEmployeeWorkDay.getId());

	}

	private EmployeeWorkDay createWorkDay(Employee employee) {

		employee = employeeRepository.save(employee);

		Date date = new Date();

		EmployeeWorkDay employeeWorkDay = new EmployeeWorkDay(null, employee, date);

		employee.getEmployeeWorkDays().add(employeeWorkDay);

		return employeeWorkDay;
	}

}
