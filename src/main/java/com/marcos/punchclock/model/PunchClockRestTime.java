package com.marcos.punchclock.model;

import java.util.Date;

import com.marcos.punchclock.util.DateUtil;

public class PunchClockRestTime {

	private static final double NO_REST_NEEDED = 4;
	private static final double REST_TIME_OF_A_HOUR_NEEDED = 6;
	private static final double HOUR = 1;
	private static final double QUARTER_OF_HOUR = 0.25;
	
	private WorkingPunchClockInterval interval;
	private Date returnDate;
	
	public PunchClockRestTime(WorkingPunchClockInterval interval, Date returnDate) {
		super();
		this.interval = interval;
		this.returnDate = returnDate;
	}

	public boolean isValidInterval() {
		
		double intervalInHours = interval.getBasicIntervalInHours();
		Date outDate = interval.getOutDate();
		
		double restInterval = DateUtil.getIntervalInHours(outDate, returnDate);
		
		if(intervalInHours <= NO_REST_NEEDED) {
		
			return true;
			
		} else if(intervalInHours > NO_REST_NEEDED && intervalInHours < REST_TIME_OF_A_HOUR_NEEDED) {
			
			return restInterval >= QUARTER_OF_HOUR;
			
		} else {
			
			return restInterval >= HOUR;
		}
		
	}
	
}
