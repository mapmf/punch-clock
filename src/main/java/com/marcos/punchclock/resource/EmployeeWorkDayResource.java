package com.marcos.punchclock.resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.services.EmployeeService;
import com.marcos.punchclock.services.EmployeeWorkDayService;

@RestController
@RequestMapping("/employee-work-days")
public class EmployeeWorkDayResource {

	@Autowired
	private EmployeeWorkDayService employeeWorkDayService;
	
	@Autowired
	private EmployeeService employeeService;
	
	
	@GetMapping("/{employee-id}")
	public ResponseEntity<EmployeeWorkDay> getWorkDay(
			@PathVariable("employee-id") String employeePis,
			@RequestParam(name = "dateInMilliseconds", defaultValue = "0") Long dateInMilliseconds) {

		Date date;

		if(dateInMilliseconds == 0) {
			date = new Date();
		} else {
			date = new Date(dateInMilliseconds);
		}
		
		Employee employee = employeeService.getByPis(employeePis);
		
		EmployeeWorkDay workDay = employeeWorkDayService.getByEmployeeAndDate(employee, date);
		
		return ResponseEntity.ok(workDay);
	}

}
