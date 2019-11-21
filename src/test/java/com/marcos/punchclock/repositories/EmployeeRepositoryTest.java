package com.marcos.punchclock.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.respositories.EmployeeRepository;

@DataJpaTest
public class EmployeeRepositoryTest {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void it_should_save_employee() {
		
		Employee employee = new Employee();
		employee.setPis("12345678910");
		employee.setName("Marcos");
		
		employee = employeeRepository.save(employee);
		
		Employee persistedEmployee = employeeRepository.findById(employee.getPis()).get();
		
		assertEquals(employee, persistedEmployee);
	}
	
}
