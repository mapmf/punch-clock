package com.marcos.punchclock.services;

import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.repositories.EmployeeRepository;
import com.marcos.punchclock.repositories.EmployeeWorkDayRepository;
import com.marcos.punchclock.repositories.PunchClockRepository;

@Service
public class DBService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	PunchClockRepository punchClockRepository;
	
	@Autowired
	EmployeeWorkDayRepository employeeWorkDayRepository;
	
	public void populateTestDataBase() {
		
		Employee e1 = new Employee("12345678910", "Bruce");
		Employee e2 = new Employee("12345678911", "Clarck");
		Employee e3 = new Employee("12345678912", "Diana");
		
		employeeRepository.saveAll(Arrays.asList(e1, e2, e3));

		Date date1 = new Date(System.currentTimeMillis() - 300000);
		Date date2 = new Date(System.currentTimeMillis() - 400000);
		Date date3 = new Date(System.currentTimeMillis() - 500000);
		
		EmployeeWorkDay ewd1 = new EmployeeWorkDay(null, e1, date1);
		EmployeeWorkDay ewd2 = new EmployeeWorkDay(null, e2, date3);
		
		ewd1 = employeeWorkDayRepository.save(ewd1);
		ewd2 = employeeWorkDayRepository.save(ewd2);
		
		PunchClock pc1 = new PunchClock(null, date1, e1, ewd1);
		PunchClock pc2 = new PunchClock(null, date2, e1, ewd1);
		PunchClock pc3 = new PunchClock(null, date3, e2, ewd2);
		
		ewd1.getPunchClocks().addAll(Arrays.asList(pc1, pc2));
		ewd2.getPunchClocks().addAll(Arrays.asList(pc3));
		
		e1.getEmployeeWorkDays().addAll(Arrays.asList(ewd1));
		e2.getEmployeeWorkDays().addAll(Arrays.asList(ewd2));
		
		
		punchClockRepository.saveAll(Arrays.asList(pc1, pc2, pc3));
		
	}
	
}
