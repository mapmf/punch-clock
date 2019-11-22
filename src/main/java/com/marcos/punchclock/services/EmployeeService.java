package com.marcos.punchclock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.repositories.EmployeeRepository;
import com.marcos.punchclock.services.exceptions.ObjectNotFoundException;


@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee getByPis(String pis) {
		
		return employeeRepository.findById(pis)
				.orElseThrow(() -> new ObjectNotFoundException("Employee with " + pis + " was not found"));
		
	}
	
}
