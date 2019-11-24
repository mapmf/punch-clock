package com.marcos.punchclock.util;

import com.marcos.punchclock.model.Employee;

public class EmployeeTestUtil {
	
	public static Employee createEmployee() {

		Employee employee = new Employee();
		
		employee.setId(TestUtil.randomOnlyNumberString(11));
		employee.setName(TestUtil.randomString(10));

		return employee;
	}
}
