package com.marcos.punchclock.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.repositories.EmployeeRepository;
import com.marcos.punchclock.repositories.PunchClockRepository;

@DataJpaTest
public class PunchClockRepositoryTest {

	@Autowired
	PunchClockRepository punchClockRepository;
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void it_should_save_punch_clock() {
		
		Date now = new Date();
		
		Employee employee = new Employee();
		employee.setName("Oliver");
		employee.setPis("12345678910");
		
		employee = employeeRepository.save(employee);
		
		PunchClock punchClock = new PunchClock();
		punchClock.setEmployee(employee);
		punchClock.setDate(now);
		
		punchClock = punchClockRepository.save(punchClock);
		
		PunchClock persistedPunchClock = punchClockRepository.findById(punchClock.getId()).get();
		
		assertEquals(punchClock, persistedPunchClock);
	}
	
}
