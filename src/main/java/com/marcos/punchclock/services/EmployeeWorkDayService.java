package com.marcos.punchclock.services;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.repositories.EmployeeWorkDayRepository;
import com.marcos.punchclock.services.exceptions.ObjectNotFoundException;
import com.marcos.punchclock.util.DateUtil;

@Service
public class EmployeeWorkDayService {

	@Autowired
	private EmployeeWorkDayRepository employeeWorkDayRepository;

	public EmployeeWorkDay createIfNotExist(EmployeeWorkDay workDay) {

		Date createdAt = workDay.getCreatedAt();

		Date beginOfDay = DateUtil.getBeginOfDay(createdAt);
		Date endOfDay = DateUtil.getEndOfDay(createdAt);

		List<EmployeeWorkDay> existingWorkDay = employeeWorkDayRepository
				.findByEmployeeAndCreatedAtBetween(workDay.getEmployee(), beginOfDay, endOfDay);

		if (existingWorkDay != null && !existingWorkDay.isEmpty()) {
			return existingWorkDay.get(0);
		}

		return employeeWorkDayRepository.save(workDay);

	}

	public EmployeeWorkDay getByEmployeeAndDate(Employee employee, Date date) {

		Date beginOfDay = DateUtil.getBeginOfDay(date);
		Date endOfDay = DateUtil.getEndOfDay(date);

		List<EmployeeWorkDay> existingWorkDays = getByEmployeeAndDateBetween(employee, beginOfDay, endOfDay);

		return existingWorkDays.get(0);
	}
	
	public List<EmployeeWorkDay> getByEmployeeAndMonth(Employee employee, Date monthDate) {

		Date beginOfMonth = DateUtil.getBeginOfMonth(monthDate);
		Date endOfMonth = DateUtil.getEndOfMonth(monthDate);
		
		List<EmployeeWorkDay> existingWorkDays = getByEmployeeAndDateBetween(employee, beginOfMonth, endOfMonth);

		return existingWorkDays;
	}

	public List<EmployeeWorkDay> getByEmployeeAndDateBetween(Employee employee, Date startDate, Date endDate) {

		List<EmployeeWorkDay> workDays = employeeWorkDayRepository.findByEmployeeAndCreatedAtBetween(employee,
				startDate, endDate);

		if (workDays.isEmpty()) {
			throw new ObjectNotFoundException("No workdays existing in interval of " + startDate + " and " + endDate);
		}

		return workDays;
	}

}
