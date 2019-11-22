package com.marcos.punchclock.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PunchClockRestTimeTest {

	@Test
	public void it_should_no_need_rest_time() {
		
		Calendar calendar = Calendar.getInstance();

		Date outDate = calendar.getTime();
		
		calendar.add(Calendar.SECOND, 1);
		
		Date returnDate = calendar.getTime();
		
		double intervalInHours = 4.0;

		WorkingPunchClockInterval interval = Mockito.mock(WorkingPunchClockInterval.class);
		Mockito.when(interval.getOutDate()).thenReturn(outDate);
		Mockito.when(interval.getBasicIntervalInHours()).thenReturn(intervalInHours);
		
		PunchClock returnPunchClock = Mockito.mock(PunchClock.class);
		Mockito.when(returnPunchClock.getDate()).thenReturn(returnDate);
		
		PunchClockRestTime restTime = new PunchClockRestTime(interval, returnDate);
		
		assertTrue(restTime.isValidInterval());
		
	}
	
	@Test
	public void it_should_need_rest_time_of_a_quarter_of_hour_but_dont_have_it() {

		Calendar calendar = Calendar.getInstance();

		Date outDate = calendar.getTime();
		
		calendar.add(Calendar.SECOND, 1);
		
		Date returnDate = calendar.getTime();
		
		double intervalInHours = 5.0;

		WorkingPunchClockInterval interval = Mockito.mock(WorkingPunchClockInterval.class);
		Mockito.when(interval.getOutDate()).thenReturn(outDate);
		Mockito.when(interval.getBasicIntervalInHours()).thenReturn(intervalInHours);
		
		PunchClock returnPunchClock = Mockito.mock(PunchClock.class);
		Mockito.when(returnPunchClock.getDate()).thenReturn(returnDate);
		
		PunchClockRestTime restTime = new PunchClockRestTime(interval, returnDate);
		
		assertFalse(restTime.isValidInterval());		
	}
	
	@Test
	public void it_should_need_rest_time_of_a_quarter_of_hour_and_have_it() {

		Calendar calendar = Calendar.getInstance();

		Date outDate = calendar.getTime();
		
		calendar.add(Calendar.MINUTE, 15);
		
		Date returnDate = calendar.getTime();
		
		double intervalInHours = 5.0;

		WorkingPunchClockInterval interval = Mockito.mock(WorkingPunchClockInterval.class);
		Mockito.when(interval.getOutDate()).thenReturn(outDate);
		Mockito.when(interval.getBasicIntervalInHours()).thenReturn(intervalInHours);
		
		PunchClock returnPunchClock = Mockito.mock(PunchClock.class);
		Mockito.when(returnPunchClock.getDate()).thenReturn(returnDate);
		
		PunchClockRestTime restTime = new PunchClockRestTime(interval, returnDate);
		
		assertTrue(restTime.isValidInterval());		
	}

	@Test
	public void it_should_need_rest_time_of_a_hour_but_dont_have_it() {
	
		Calendar calendar = Calendar.getInstance();

		Date outDate = calendar.getTime();
		
		calendar.add(Calendar.MINUTE, 15);
		
		Date returnDate = calendar.getTime();
		
		double intervalInHours = 7.0;

		WorkingPunchClockInterval interval = Mockito.mock(WorkingPunchClockInterval.class);
		Mockito.when(interval.getOutDate()).thenReturn(outDate);
		Mockito.when(interval.getBasicIntervalInHours()).thenReturn(intervalInHours);
		
		PunchClock returnPunchClock = Mockito.mock(PunchClock.class);
		Mockito.when(returnPunchClock.getDate()).thenReturn(returnDate);
		
		PunchClockRestTime restTime = new PunchClockRestTime(interval, returnDate);
		
		assertFalse(restTime.isValidInterval());
		
	}
	
	public void it_should_need_rest_time_of_a_hour_and_have_it() {
		
		Calendar calendar = Calendar.getInstance();

		Date outDate = calendar.getTime();
		
		calendar.add(Calendar.HOUR, 1);
		
		Date returnDate = calendar.getTime();
		
		double intervalInHours = 7.0;

		WorkingPunchClockInterval interval = Mockito.mock(WorkingPunchClockInterval.class);
		Mockito.when(interval.getOutDate()).thenReturn(outDate);
		Mockito.when(interval.getBasicIntervalInHours()).thenReturn(intervalInHours);
		
		PunchClock returnPunchClock = Mockito.mock(PunchClock.class);
		Mockito.when(returnPunchClock.getDate()).thenReturn(returnDate);
		
		PunchClockRestTime restTime = new PunchClockRestTime(interval, returnDate);
		
		assertTrue(restTime.isValidInterval());
	}
}
