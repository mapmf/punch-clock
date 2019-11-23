package com.marcos.punchclock.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.PunchClock;

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

		PunchClock punchClock = new PunchClock();
		punchClock.setEmployee(employee);
		punchClock.setDate(now);

		punchClock = punchClockRepository.save(punchClock);

		PunchClock persistedPunchClock = punchClockRepository.findById(punchClock.getId()).get();

		assertEquals(punchClock, persistedPunchClock);
	}

	@Test
	public void it_should_get_punch_clock_in_defined_interval() {

		Date endDate = new Date();
		Date startDate = new Date(endDate.getTime() - 60000);

		Employee employee = persistEmployee();

		PunchClock punchClock = new PunchClock();
		punchClock.setEmployee(employee);
		punchClock.setDate(startDate);

		punchClock = punchClockRepository.save(punchClock);

		PunchClock persitedPunchClock = punchClockRepository.findByEmployeeAndDateBetween(employee, startDate, endDate);
		
		assertEquals(punchClock, persitedPunchClock);
	}

	private Employee persistEmployee() {

		Employee employee = new Employee();
		employee.setName("Oliver");
		employee.setId("12345678910");

		employee = employeeRepository.save(employee);
		
		return employee;
	}

}
