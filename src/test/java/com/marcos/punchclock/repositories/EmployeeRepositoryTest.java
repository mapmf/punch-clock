package com.marcos.punchclock.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.repositories.EmployeeRepository;
import com.marcos.punchclock.util.EmployeeTestUtil;

@DataJpaTest
public class EmployeeRepositoryTest {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Test
	public void it_should_save_employee() {
		
		Employee expectedEmployee = EmployeeTestUtil.createEmployee();
		
		expectedEmployee = employeeRepository.save(expectedEmployee);
		
		Employee actualEmployee = employeeRepository.findById(expectedEmployee.getId()).get();
		
		assertEquals(expectedEmployee, actualEmployee);
	}
	
}
