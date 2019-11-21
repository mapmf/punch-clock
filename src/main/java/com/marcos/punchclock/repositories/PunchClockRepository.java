package com.marcos.punchclock.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.PunchClock;

public interface PunchClockRepository extends JpaRepository<PunchClock, Integer>{

	public PunchClock findByEmployeeAndDateBetween(Employee employee, Date startDate, Date endDate);
	
}
