package com.marcos.punchclock.resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.services.EmployeeService;
import com.marcos.punchclock.services.PunchClockService;

@RestController
@RequestMapping("/punch-clock")
public class PunchClockResource {

	@Autowired
	private PunchClockService punchClockService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/{employee-id}")
	public ResponseEntity<Void> add(@PathVariable("employee-id") String employeePis){
	
		Date now = new Date();
		
		Employee employee = employeeService.getByPis(employeePis);
		
		PunchClock newPunchClock = new PunchClock();
		newPunchClock.setDate(now);
		newPunchClock.setEmployee(employee);
		
		punchClockService.insert(newPunchClock);
		
		return ResponseEntity.created(null).build();
	}
	
}
