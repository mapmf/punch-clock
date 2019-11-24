package com.marcos.punchclock.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.marcos.punchclock.dto.EmployeeNewDTO;
import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.repositories.EmployeeRepository;
import com.marcos.punchclock.services.exceptions.ObjectNotFoundException;
import com.marcos.punchclock.services.exceptions.EmployeeAlreadyExistException;

/**
 * 
 * Service responsible for Employee management
 * 
 * @author Marcos André
 *
 */

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private BCryptPasswordEncoder pe;
	
	public Employee getById(String id) {

		return employeeRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Employee with " + id + " was not found"));

	}
	
	public Employee insert(Employee employee) {
		
		Optional<Employee> existingEmployee = employeeRepository.findById(employee.getId());
		
		if(existingEmployee.isPresent()) {
			throw new EmployeeAlreadyExistException("Employee with " + employee.getId() + " already exists");
		}
		
		return employeeRepository.save(employee);
	}
	
	public List<Employee> list() {
		
		return employeeRepository.findAll();
	}

	public Employee fromDTO(EmployeeNewDTO dto) {

		return new Employee(dto.getId(), dto.getName(), pe .encode(dto.getPassword()));

	}

}
