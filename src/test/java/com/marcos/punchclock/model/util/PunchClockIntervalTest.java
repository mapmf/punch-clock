package com.marcos.punchclock.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.marcos.punchclock.model.PunchClock;
import com.marcos.punchclock.model.util.WorkingPunchClockInterval;

public class PunchClockIntervalTest {

	@Test
	public void it_should_calculate_interval() {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_WEEK, 2);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		
		Date inDate = calendar.getTime();
		
		calendar.set(Calendar.HOUR_OF_DAY, 14);
		
		Date outDate = calendar.getTime();
		
		PunchClock inPunchClock = Mockito.mock(PunchClock.class);
		PunchClock outPunchClock = Mockito.mock(PunchClock.class);
		
		Mockito.when(inPunchClock.getDate()).thenReturn(inDate);
		Mockito.when(outPunchClock.getDate()).thenReturn(outDate);
		
		WorkingPunchClockInterval interval = new WorkingPunchClockInterval(inPunchClock, outPunchClock);
		
		double expected = 360;
		
		double actual = interval.calculateMinutes();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void it_should_calculate_interval_on_saturday() {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_WEEK, 7);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		
		Date inDate = calendar.getTime();
		
		calendar.set(Calendar.HOUR_OF_DAY, 14);
		
		Date outDate = calendar.getTime();
		
		PunchClock inPunchClock = Mockito.mock(PunchClock.class);
		PunchClock outPunchClock = Mockito.mock(PunchClock.class);
		
		Mockito.when(inPunchClock.getDate()).thenReturn(inDate);
		Mockito.when(outPunchClock.getDate()).thenReturn(outDate);
		
		WorkingPunchClockInterval interval = new WorkingPunchClockInterval(inPunchClock, outPunchClock);
		
		double expected = 540;
		
		double actual = interval.calculateMinutes();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void it_should_calculate_interval_on_sunday() {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_WEEK, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 8);
		
		Date inDate = calendar.getTime();
		
		calendar.set(Calendar.HOUR_OF_DAY, 14);
		
		Date outDate = calendar.getTime();
		
		PunchClock inPunchClock = Mockito.mock(PunchClock.class);
		PunchClock outPunchClock = Mockito.mock(PunchClock.class);
		
		Mockito.when(inPunchClock.getDate()).thenReturn(inDate);
		Mockito.when(outPunchClock.getDate()).thenReturn(outDate);
		
		WorkingPunchClockInterval interval = new WorkingPunchClockInterval(inPunchClock, outPunchClock);
		
		double expected = 720;
		
		double actual = interval.calculateMinutes();
		
		assertEquals(expected, actual);
	}
	
	@Test
	public void it_should_calculate_interval_at_night() {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.set(Calendar.DAY_OF_WEEK, 5);
		calendar.set(Calendar.HOUR_OF_DAY, 22);
		
		Date inDate = calendar.getTime();
		
		calendar.set(Calendar.HOUR_OF_DAY, 4);
		calendar.set(Calendar.DAY_OF_WEEK, 6);
		
		Date outDate = calendar.getTime();
		
		PunchClock inPunchClock = Mockito.mock(PunchClock.class);
		PunchClock outPunchClock = Mockito.mock(PunchClock.class);
		
		Mockito.when(inPunchClock.getDate()).thenReturn(inDate);
		Mockito.when(outPunchClock.getDate()).thenReturn(outDate);
		
		WorkingPunchClockInterval interval = new WorkingPunchClockInterval(inPunchClock, outPunchClock);
		
		int delta = 2;
		double expected = 432;
		double actual = interval.calculateMinutes();
		
		assertEquals(expected, actual, delta);
	}
}
