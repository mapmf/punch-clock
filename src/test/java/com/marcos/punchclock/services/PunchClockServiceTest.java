package com.marcos.punchclock.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.repositories.PunchClockRepository;
import com.marcos.punchclock.services.exceptions.InvalidPuchClockException;

@SpringBootTest
public class PunchClockServiceTest {

	@MockBean
	private PunchClockRepository punchClockRepository;
	
	@MockBean
	private EmployeeWorkDayService employeeWorkDayService;

	@Autowired
	PunchClockService punchClockService;

	@Test
	public void it_should_save_punch_clock() {

		PunchClock punchClock = new PunchClock();

		Mockito.when(punchClockRepository.findByEmployeeAndDateBetween(Mockito.any(), Mockito.any(), Mockito.any()))
				.thenReturn(null);

		Mockito.when(punchClockRepository.save(punchClock)).thenReturn(punchClock);
		
		EmployeeWorkDay workDay = createWorkDay();
		
		Mockito.when(employeeWorkDayService.createIfNotExist(workDay)).thenReturn(workDay);

		PunchClock persistedPunchClock = punchClockService.insert(punchClock);

		assertEquals(punchClock, persistedPunchClock);
	}

	@Test
	public void it_should_throw_exception_when_try_saving_two_punch_click_in_less_than_a_minute() {

		try {

			Employee employee = createEmployee();

			Calendar calendar = Calendar.getInstance();

			Date startDate = calendar.getTime();

			calendar.add(Calendar.MINUTE, 1);

			Date endDate = calendar.getTime();

			PunchClock firstPunchClock = new PunchClock();

			firstPunchClock.setEmployee(employee);
			firstPunchClock.setDate(startDate);

			Mockito.when(punchClockRepository.findByEmployeeAndDateBetween(Mockito.any(), Mockito.any(), Mockito.any()))
					.thenReturn(firstPunchClock);

			PunchClock secondPunchClock = new PunchClock();

			secondPunchClock.setEmployee(employee);
			secondPunchClock.setDate(endDate);

			punchClockService.insert(secondPunchClock);

			fail();

		} catch (InvalidPuchClockException e) {

			// Success
		}

	}

	private Employee createEmployee() {

		Employee employee = new Employee();
		employee.setName("Oliver");
		employee.setPis("12345678910");

		return employee;
	}
	
	private EmployeeWorkDay createWorkDay() {

		Employee employee = createEmployee();

		Date date = new Date();

		return new EmployeeWorkDay(null, employee, date);
	}
}
