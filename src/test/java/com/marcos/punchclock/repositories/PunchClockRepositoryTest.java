package com.marcos.punchclock.repositories;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.util.EmployeeTestUtil;

@DataJpaTest
public class PunchClockRepositoryTest {

	@Autowired
	PunchClockRepository punchClockRepository;

	@Autowired
	EmployeeRepository employeeRepository;

	@Test
	public void it_should_save_punch_clock() {

		Date now = new Date();

		Employee employee = persistEmployee();

		PunchClock expectedPunchClock = new PunchClock();
		expectedPunchClock.setEmployee(employee);
		expectedPunchClock.setDate(now);

		punchClockRepository.save(expectedPunchClock);

		PunchClock actualPunchClock = punchClockRepository.findById(expectedPunchClock.getId()).get();

		assertNotNull(actualPunchClock);
	}

	@Test
	public void it_should_get_punch_clock_in_defined_interval() {

		Date endDate = new Date();
		Date startDate = new Date(endDate.getTime() - 60000);

		Employee employee = persistEmployee();

		PunchClock expectedPunchClock = new PunchClock();
		expectedPunchClock.setEmployee(employee);
		expectedPunchClock.setDate(startDate);

		punchClockRepository.save(expectedPunchClock);

		PunchClock actualPunchClock = punchClockRepository.findByEmployeeAndDateBetween(employee, startDate, endDate);
		
		assertNotNull(actualPunchClock);
	}

	private Employee persistEmployee() {

		Employee employee = EmployeeTestUtil.createEmployee();
		
		employee = employeeRepository.save(employee);
		
		return employee;
	}

}
