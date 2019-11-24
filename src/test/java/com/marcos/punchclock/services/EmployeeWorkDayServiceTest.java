package com.marcos.punchclock.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.repositories.EmployeeWorkDayRepository;
import com.marcos.punchclock.util.EmployeeTestUtil;

@SpringBootTest
public class EmployeeWorkDayServiceTest {

	@MockBean
	EmployeeWorkDayRepository employeeWorkDayRepository;

	@Autowired
	EmployeeWorkDayService employeeWorkDayService;

	@Test
	public void it_should_save_work_day() {

		Employee employee = EmployeeTestUtil.createEmployee();
		
		EmployeeWorkDay expectedEmployeeWorkDay = createWorkDay(employee);
		expectedEmployeeWorkDay.setId(1);

		Mockito.when(employeeWorkDayRepository.findByEmployeeAndCreatedAtBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(null);

		Mockito.when(employeeWorkDayRepository.save(expectedEmployeeWorkDay)).thenReturn(expectedEmployeeWorkDay);

		EmployeeWorkDay actualWorkDay = employeeWorkDayService.createIfNotExist(expectedEmployeeWorkDay);

		assertEquals(expectedEmployeeWorkDay.getId(), actualWorkDay.getId());
	}

	@Test
	public void it_should_get_work_day() {

		Employee employee = EmployeeTestUtil.createEmployee();
		EmployeeWorkDay expectedEmployeeWorkDay = createWorkDay(employee);
		expectedEmployeeWorkDay.setId(1);

		Mockito.when(employeeWorkDayRepository.findByEmployeeAndCreatedAtBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(Arrays.asList(expectedEmployeeWorkDay));

		Employee expectedEmployee = expectedEmployeeWorkDay.getEmployee();
		Date expectedCreatedAt = expectedEmployeeWorkDay.getCreatedAt();

		EmployeeWorkDay actualpersitedEmployeeWorkDay = employeeWorkDayService.getByEmployeeAndDate(
				expectedEmployee, expectedCreatedAt);

		assertEquals(expectedEmployeeWorkDay.getId(), actualpersitedEmployeeWorkDay.getId());
	}
	
	@Test
	public void it_should_get_work_month() {

		Employee employee = EmployeeTestUtil.createEmployee();
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.YEAR, 2019);
		calendar.set(Calendar.MONTH, 10);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		
		EmployeeWorkDay ewd1 = createWorkDay(employee);
		ewd1.setId(1);
		ewd1.setCreatedAt(calendar.getTime());

		calendar.add(Calendar.DAY_OF_MONTH, 2);
		
		EmployeeWorkDay ewd2 = createWorkDay(employee);
		ewd2.setId(2);
		ewd2.setCreatedAt(calendar.getTime());
		
		calendar.add(Calendar.MONTH, -1);
		
		EmployeeWorkDay ewd3 = createWorkDay(employee);
		ewd3.setId(3);
		ewd3.setCreatedAt(calendar.getTime());
		
		Mockito.when(employeeWorkDayRepository.findByEmployeeAndCreatedAtBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(Arrays.asList(ewd1, ewd2));
		
		Date dateMonth = ewd1.getCreatedAt();
		
		List<EmployeeWorkDay> byEmployeeAndMonth = employeeWorkDayService.getByEmployeeAndMonth(employee, dateMonth);

		assertEquals(ewd1.getId(), byEmployeeAndMonth.get(0).getId());
		assertEquals(ewd2.getId(), byEmployeeAndMonth.get(1).getId());
	}

	private EmployeeWorkDay createWorkDay(Employee employee) {

		Date date = new Date();

		return new EmployeeWorkDay(null, employee, date);
	}

}
