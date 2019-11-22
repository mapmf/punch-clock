package com.marcos.punchclock.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.repositories.EmployeeWorkDayRepository;
import com.marcos.punchclock.util.DateUtil;

@Service
public class EmployeeWorkDayService {

	@Autowired
	private EmployeeWorkDayRepository employeeWorkDayRepository;

	public EmployeeWorkDay createIfNotExist(EmployeeWorkDay workDay) {

		Date createdAt = workDay.getCreatedAt();

		Date beginOfDay = DateUtil.getBeginOfDay(createdAt);
		Date endOfDay = DateUtil.getEndOfDay(createdAt);

		List<EmployeeWorkDay> existingWorkDay = employeeWorkDayRepository.findByEmployeeAndCreatedAtBetween(workDay.getEmployee(), beginOfDay, endOfDay);
		
		if(existingWorkDay!= null && !existingWorkDay.isEmpty()) {
			return existingWorkDay.get(0);
		}

		return employeeWorkDayRepository.save(workDay);

	}

}
