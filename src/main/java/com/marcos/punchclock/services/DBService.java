package com.marcos.punchclock.services;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.model.enums.Profile;
import com.marcos.punchclock.repositories.EmployeeRepository;
import com.marcos.punchclock.repositories.EmployeeWorkDayRepository;
import com.marcos.punchclock.repositories.PunchClockRepository;

/**
 * 
 * Service responsible for populate database according
 * to defined profile
 * 
 * @author Marcos André
 *
 */

@Service
public class DBService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PunchClockRepository punchClockRepository;

	@Autowired
	private EmployeeWorkDayRepository employeeWorkDayRepository;

	@Autowired
	private BCryptPasswordEncoder pe;

	public void populateProdDatabase() {
		
		Employee e = new Employee("admin", "Administrator", pe.encode("admin"));
		e.addProfile(Profile.ADMIN);
		
		Optional<Employee> existingAdmin = employeeRepository.findById(e.getId());
		
		if(!existingAdmin.isPresent()) {
			employeeRepository.save(e);
		}
		
	}
	
	public void populateDevDataBase() {

		Employee admin = new Employee("admin", "Administrator", pe.encode("admin"));
		admin.addProfile(Profile.ADMIN);
		
		Employee e1 = new Employee("12345678910", "Bruce", pe.encode("123"));
		Employee e2 = new Employee("12345678911", "Clarck", pe.encode("123"));
		Employee e3 = new Employee("12345678912", "Diana", pe.encode("123"));

		employeeRepository.saveAll(Arrays.asList(admin, e1, e2, e3));

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		Date date1 = calendar.getTime();

		calendar.add(Calendar.HOUR, 3);

		Date date2 = calendar.getTime();

		calendar.add(Calendar.MINUTE, 30);

		Date date3 = calendar.getTime();

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
