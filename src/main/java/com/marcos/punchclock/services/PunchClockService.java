package com.marcos.punchclock.services;

import java.util.Calendar;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.repositories.PunchClockRepository;
import com.marcos.punchclock.services.exceptions.InvalidPuchClockException;

/**
 * 
 * Service responsible for PunchClock management
 * 
 * @author Marcos André
 *
 */

@Service
public class PunchClockService {

	@Autowired
	PunchClockRepository punchClockRepository;
	
	@Autowired
	EmployeeWorkDayService employeeWorkDayService;

	@Transactional
	public PunchClock insert(PunchClock newPunchClock) {

		if (hasPuchClockAddedInLastMinute(newPunchClock)) {
			throw new InvalidPuchClockException("You punched clock less than 1 minute ago");
		}

		EmployeeWorkDay workDay = new EmployeeWorkDay();
		workDay.setEmployee(newPunchClock.getEmployee());
		workDay.setCreatedAt(newPunchClock.getDate());
		
		workDay = employeeWorkDayService.createIfNotExist(workDay);
		
		
		newPunchClock.setId(null);
		newPunchClock.setEmployeeWorkDay(workDay);

		return punchClockRepository.save(newPunchClock);

	}

	private boolean hasPuchClockAddedInLastMinute(PunchClock newPunchClock) {

		Date date = new Date();

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, -1);

		Date previousDate = calendar.getTime();

		newPunchClock.setDate(date);

		PunchClock existingPunchClock = punchClockRepository.findByEmployeeAndDateBetween(newPunchClock.getEmployee(),
				previousDate, date);

		return existingPunchClock != null;
	}

}
