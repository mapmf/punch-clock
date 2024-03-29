package com.marcos.punchclock.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EmployeeWorkDayTest {
	
	@Test
	public void it_should_return_interval_as_working_hours() {
		
		int expected = 240;

		Calendar calendar = Calendar.getInstance();

		calendar.set(Calendar.DAY_OF_WEEK, 4);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 13);
		
		PunchClock mock1 = Mockito.mock(PunchClock.class);
		Mockito.when(mock1.getDate()).thenReturn(calendar.getTime());

		calendar.add(Calendar.HOUR, 4);

		PunchClock mock2 = Mockito.mock(PunchClock.class);
		Mockito.when(mock2.getDate()).thenReturn(calendar.getTime());
		

		EmployeeWorkDay workDay = new EmployeeWorkDay();
		workDay.getPunchClocks().addAll(Arrays.asList(mock1, mock2));
		
		double actual = workDay.calculateWorkingMinutes();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void it_should_sum_intervals_as_working_hours() {
		
		int expected = 480;

		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_WEEK, 4);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 32);
		
		
		PunchClock mock1 = Mockito.mock(PunchClock.class);
		Mockito.when(mock1.getDate()).thenReturn(calendar.getTime());

		calendar.add(Calendar.HOUR, 4);

		PunchClock mock2 = Mockito.mock(PunchClock.class);
		Mockito.when(mock2.getDate()).thenReturn(calendar.getTime());
		
		calendar.add(Calendar.HOUR, 1);
		
		PunchClock mock3 = Mockito.mock(PunchClock.class);
		Mockito.when(mock3.getDate()).thenReturn(calendar.getTime());
		
		
		calendar.add(Calendar.HOUR, 4);
		
		PunchClock mock4 = Mockito.mock(PunchClock.class);
		Mockito.when(mock4.getDate()).thenReturn(calendar.getTime());
		

		EmployeeWorkDay workDay = new EmployeeWorkDay();
		workDay.getPunchClocks().addAll(Arrays.asList(mock1, mock2, mock3, mock4));
		
		double actual = workDay.calculateWorkingMinutes();
		
		assertEquals(expected, actual);
		
	}

	@Test
	public void it_should_sum_odd_intervals_as_working_hours() {
		
		int expected = 240;

		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_WEEK, 4);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 53);
		
		
		PunchClock mock1 = Mockito.mock(PunchClock.class);
		Mockito.when(mock1.getDate()).thenReturn(calendar.getTime());

		calendar.add(Calendar.HOUR, 4);

		PunchClock mock2 = Mockito.mock(PunchClock.class);
		Mockito.when(mock2.getDate()).thenReturn(calendar.getTime());
		
		calendar.add(Calendar.HOUR, 1);
		
		PunchClock mock3 = Mockito.mock(PunchClock.class);
		Mockito.when(mock3.getDate()).thenReturn(calendar.getTime());
		

		EmployeeWorkDay workDay = new EmployeeWorkDay();
		workDay.getPunchClocks().addAll(Arrays.asList(mock1, mock2, mock3));
		
		double actual = workDay.calculateWorkingMinutes();
		
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void it_should_get_formatted_work_hours() {
		
		String expected = "8:32";

		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_WEEK, 4);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 53);
		
		
		PunchClock mock1 = Mockito.mock(PunchClock.class);
		Mockito.when(mock1.getDate()).thenReturn(calendar.getTime());

		calendar.add(Calendar.HOUR, 4);

		PunchClock mock2 = Mockito.mock(PunchClock.class);
		Mockito.when(mock2.getDate()).thenReturn(calendar.getTime());
		
		calendar.add(Calendar.HOUR, 1);
		
		PunchClock mock3 = Mockito.mock(PunchClock.class);
		Mockito.when(mock3.getDate()).thenReturn(calendar.getTime());
		
		
		calendar.add(Calendar.HOUR, 4);
		calendar.add(Calendar.MINUTE, 32);
		
		PunchClock mock4 = Mockito.mock(PunchClock.class);
		Mockito.when(mock4.getDate()).thenReturn(calendar.getTime());
		

		EmployeeWorkDay workDay = new EmployeeWorkDay();
		workDay.getPunchClocks().addAll(Arrays.asList(mock1, mock2, mock3, mock4));
		
		String actual = workDay.getWorkingHours();
		
		assertEquals(expected, actual);
		
	}

}
