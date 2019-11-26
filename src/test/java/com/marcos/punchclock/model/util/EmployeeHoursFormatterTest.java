package com.marcos.punchclock.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeeHoursFormatterTest {

	@Test
	public void it_should_format_hours() {
		
		assertHours(15.0, "0:15");
		assertHours(30.0, "0:30");
		assertHours(45.0, "0:45");
		assertHours(60.0, "1:00");
		assertHours(120.0, "2:00");
	}
	
	private void assertHours(Double hours, String expected) {
		
		String actual = EmployeeHoursFormatterUtil.format(hours);
		
		assertEquals(expected, actual);
	}
}
