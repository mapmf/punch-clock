package com.marcos.punchclock.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marcos.punchclock.dto.EmployeeNewDTO;
import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeResource {
	
	@Autowired
	private EmployeeService employeeService;
	
	/**
	* It creates a new employee
	*/
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody EmployeeNewDTO dto){
			
		Employee employee = employeeService.fromDTO(dto);
		
		employeeService.insert(employee);
		
		return ResponseEntity.created(null).build();
	}

	/**
	* It lists all employees
	*/
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@GetMapping
	public ResponseEntity<List<Employee>> list(){
			
		List<Employee> list = employeeService.list();
		
		return ResponseEntity.ok(list);
	}
	
}
