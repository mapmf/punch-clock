package com.marcos.punchclock.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.marcos.punchclock.model.Employee;
import com.marcos.punchclock.model.enums.Profile;

public class EmployeeTestUtil {

	private static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public static Employee createEmployee() {

		String id = TestUtil.randomOnlyNumberString(11);
		String password = TestUtil.randomString(6);
		
		return createEmployee(id, password);
	}
	
	public static Employee createEmployee(String id, String password) {

		Employee employee = new Employee();
		employee.setName(TestUtil.randomString(10));
		employee.setId(id);
		employee.setPassword(encoder.encode(password));

		return employee;
	}
	
	public static Employee createAdminEmployee() {
		
		String adminPassword = "admin";

		Employee admin = new Employee();
		admin.setId("admin");
		admin.setName("admin");
		admin.setPassword(encoder.encode(adminPassword));
		admin.addProfile(Profile.ADMIN);

		return admin;
	}
	
	
}
