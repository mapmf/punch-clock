package com.marcos.punchclock.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;

@Repository
public interface EmployeeWorkDayRepository extends JpaRepository<EmployeeWorkDay, Integer>{

	public List<EmployeeWorkDay> findByEmployeeAndCreatedAtBetween(Employee employee, Date startDate, Date endDate);
}
