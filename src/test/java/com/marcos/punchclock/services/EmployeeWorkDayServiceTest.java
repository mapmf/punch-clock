package com.marcos.punchclock.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.repositories.EmployeeWorkDayRepository;

@SpringBootTest
public class EmployeeWorkDayServiceTest {

	@MockBean
	EmployeeWorkDayRepository employeeWorkDayRepository;

	@Autowired
	EmployeeWorkDayService employeeWorkDayService;

	@Test
	public void it_should_save_work_day() {

		EmployeeWorkDay employeeWorkDay = createObj();

		EmployeeWorkDay expectedpersitedEmployeeWorkDay = createObj();
		expectedpersitedEmployeeWorkDay.setId(1);
		
		Mockito.when(employeeWorkDayRepository.findByEmployeeAndCreatedAtBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(null);
		
		Mockito.when(employeeWorkDayRepository.save(employeeWorkDay)).thenReturn(expectedpersitedEmployeeWorkDay);
		
		EmployeeWorkDay actualpersitedEmployeeWorkDay = employeeWorkDayService.createIfNotExist(employeeWorkDay);
		
		assertEquals(expectedpersitedEmployeeWorkDay.getId(), actualpersitedEmployeeWorkDay.getId());
	}
	
	@Test
	public void it_should_get_work_day() {

		EmployeeWorkDay employeeWorkDay = createObj();
		employeeWorkDay.setId(1);
		
		EmployeeWorkDay expectedpersitedEmployeeWorkDay = createObj();
		expectedpersitedEmployeeWorkDay.setId(2);
		
		Mockito.when(employeeWorkDayRepository.findByEmployeeAndCreatedAtBetween(Mockito.any(), Mockito.any(),
				Mockito.any())).thenReturn(expectedpersitedEmployeeWorkDay);
		
		EmployeeWorkDay actualpersitedEmployeeWorkDay = employeeWorkDayService.createIfNotExist(employeeWorkDay);
		
		assertEquals(expectedpersitedEmployeeWorkDay.getId(), actualpersitedEmployeeWorkDay.getId());
	}

	private EmployeeWorkDay createObj() {

		Employee employee = new Employee();
		employee.setPis("12345678910");
		employee.setName("Marcos");

		Date date = new Date();

		return new EmployeeWorkDay(null, employee, date);
	}

}
