package com.marcos.punchclock.resource;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.services.EmployeeService;
import com.marcos.punchclock.services.PunchClockService;
import com.marcos.punchclock.services.UserService;

@RestController
@RequestMapping("/punch-clocks")
public class PunchClockResource {

	@Autowired
	private PunchClockService punchClockService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Autowired
	private UserService userService;
	
	/** 
	* It adds a new punch clock to a employee
	*/

	@PostMapping
	public ResponseEntity<Void> add(){
	
		Date now = new Date();
		
		UserDetails userDetails = userService.authenticated();
		
		Employee employee = employeeService.getById(userDetails.getUsername());
		
		PunchClock newPunchClock = new PunchClock();
		newPunchClock.setDate(now);
		newPunchClock.setEmployee(employee);
		
		punchClockService.insert(newPunchClock);
		
		return ResponseEntity.created(null).build();
	}
	
}
