package com.marcos.punchclock.resource;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
import com.marcos.punchclock.services.UserService;
import com.marcos.punchclock.util.DateUtil;

@RestController
@RequestMapping("/employee-work-days")
public class EmployeeWorkDayResource {

	@Autowired
	private EmployeeWorkDayService employeeWorkDayService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserService userService;

	@GetMapping("/{employee-id}")
	public ResponseEntity<EmployeeWorkDay> getWorkDayByEmployeeId(@PathVariable("employee-id") String employeePis,
			@RequestParam(name = "dateInMilliseconds", defaultValue = "0") Long dateInMilliseconds) {

		Date date;

		if (dateInMilliseconds == 0) {
			date = new Date();
		} else {
			date = new Date(dateInMilliseconds);
		}

		Employee employee = employeeService.getById(employeePis);

		EmployeeWorkDay workDay = employeeWorkDayService.getByEmployeeAndDate(employee, date);

		return ResponseEntity.ok(workDay);
	}

	@GetMapping
	public ResponseEntity<EmployeeWorkDay> getWorkDay(
			@RequestParam(name = "dateInMilliseconds", defaultValue = "0") Long dateInMilliseconds) {

		Date date;

		if (dateInMilliseconds == 0) {
			date = new Date();
		} else {
			date = new Date(dateInMilliseconds);
		}

		UserDetails authenticated = userService.authenticated();

		Employee employee = employeeService.getById(authenticated.getUsername());

		EmployeeWorkDay workDay = employeeWorkDayService.getByEmployeeAndDate(employee, date);

		return ResponseEntity.ok(workDay);
	}

	@GetMapping("/get-by-month/{employee-id}")
	public ResponseEntity<EmployeeWorkMonthDTO> getWorkDaysByMonthByEmployeeId(
			@PathVariable("employee-id") String employeePis,
			@RequestParam(name = "month", defaultValue = "0") Integer month,
			@RequestParam(name = "year", defaultValue = "0") Integer year) {

		Date monthDate = DateUtil.getMonthDate(month, year);

		Employee employee = employeeService.getById(employeePis);

		List<EmployeeWorkDay> byEmployeeAndMonth = employeeWorkDayService.getByEmployeeAndMonth(employee, monthDate);

		EmployeeWorkMonthDTO dto = new EmployeeWorkMonthDTO(monthDate, byEmployeeAndMonth);

		return ResponseEntity.ok(dto);
	}

	@GetMapping("/get-by-month")
	public ResponseEntity<EmployeeWorkMonthDTO> getWorkDaysByMonth(
			@RequestParam(name = "month", defaultValue = "0") Integer month,
			@RequestParam(name = "year", defaultValue = "0") Integer year) {

		Date monthDate = DateUtil.getMonthDate(month, year);

		UserDetails authenticated = userService.authenticated();

		Employee employee = employeeService.getById(authenticated.getUsername());

		List<EmployeeWorkDay> byEmployeeAndMonth = employeeWorkDayService.getByEmployeeAndMonth(employee, monthDate);

		EmployeeWorkMonthDTO dto = new EmployeeWorkMonthDTO(monthDate, byEmployeeAndMonth);

		return ResponseEntity.ok(dto);
	}

}
