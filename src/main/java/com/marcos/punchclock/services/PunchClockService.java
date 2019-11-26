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
import com.marcos.punchclock.util.DateUtil;

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

		Date date = new Date();
		
		if (existPunchClockInMinute(newPunchClock, date)) {
			throw new InvalidPuchClockException("You punched clock less than 1 minute ago");
		}

		EmployeeWorkDay workDay = new EmployeeWorkDay();
		workDay.setEmployee(newPunchClock.getEmployee());
		workDay.setCreatedAt(newPunchClock.getDate());
		
		workDay = employeeWorkDayService.createIfNotExist(workDay);
		
		
		newPunchClock.setId(null);
		newPunchClock.setEmployeeWorkDay(workDay);
		newPunchClock.setDate(date);

		return punchClockRepository.save(newPunchClock);

	}

	private boolean existPunchClockInMinute(PunchClock newPunchClock, Date date) {

		Date previousDate = DateUtil.ignoringSeconds(date);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(previousDate);
		calendar.set(Calendar.SECOND, 59);

		Date limitDate = calendar.getTime();

		PunchClock existingPunchClock = punchClockRepository.findByEmployeeAndDateBetween(newPunchClock.getEmployee(),
				previousDate, limitDate);

		return existingPunchClock != null;
	}

}
