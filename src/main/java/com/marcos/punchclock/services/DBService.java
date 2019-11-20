package com.marcos.punchclock.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.respositories.EmployeeRepository;

@Service
public class DBService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public void populateTestDataBase() {
		
		Employee e1 = new Employee("12345678910", "Bruce");
		Employee e2 = new Employee("12345678911", "Clarck");
		Employee e3 = new Employee("12345678912", "Diana");
		
		employeeRepository.saveAll(Arrays.asList(e1, e2, e3));
	}
	
}
