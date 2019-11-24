package com.marcos.punchclock.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class EmployeeHoursFormatterTest {

	@Test
	public void it_should_format_hours() {
		
		assertHours(0.25, "0:15");
		assertHours(0.5, "0:30");
		assertHours(0.75, "0:45");
		assertHours(0.81, "0:48");
		assertHours(1.0, "1:00");
		assertHours(2.0, "2:00");
	}
	
	private void assertHours(Double hours, String expected) {
		
		String actual = EmployeeHoursFormatterUtil.format(hours);
		
		assertEquals(expected, actual);
	}
}
