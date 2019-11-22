package com.marcos.punchclock.resource;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.punchclock.dto.EmployeeWorkMonthDTO;
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
	
	@GetMapping("/get-by-month/{employee-id}")
	public ResponseEntity<EmployeeWorkMonthDTO> getWorkDaysByMonth(
			@PathVariable("employee-id") String employeePis,
			@RequestParam(name = "month", defaultValue = "0") Integer month,
			@RequestParam(name = "year", defaultValue = "0") Integer year) {
		
		Date now = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		if(month > 0) {
			calendar.set(Calendar.MONTH, month);
		}
		
		if(year > 0) {
			calendar.set(Calendar.YEAR, year);
		}
		
		Date monthDate = calendar.getTime();
		
		Employee employee = employeeService.getByPis(employeePis);
		
		List<EmployeeWorkDay> byEmployeeAndMonth = employeeWorkDayService.getByEmployeeAndMonth(employee, monthDate);
		
		EmployeeWorkMonthDTO dto = new EmployeeWorkMonthDTO(monthDate, byEmployeeAndMonth);
		
		return ResponseEntity.ok(dto);
	}

}
