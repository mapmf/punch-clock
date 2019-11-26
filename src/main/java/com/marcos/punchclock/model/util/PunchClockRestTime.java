package com.marcos.punchclock.model.util;

import java.util.Date;

import com.marcos.punchclock.util.DateUtil;

/**
 * 
 * This class checks if interval between
 * outDate of working interval and time of employee return
 * is a valid rest time 
 * 
 * @author Marcos André
 *
 */

public class PunchClockRestTime {

	private static final double NO_REST_NEEDED = 4*60;
	private static final double REST_TIME_OF_A_HOUR_NEEDED = 6*60;
	private static final double HOUR = 60;
	private static final double QUARTER_OF_HOUR = 15;
	
	private WorkingPunchClockInterval interval;
	private Date returnDate;
	
	public PunchClockRestTime(WorkingPunchClockInterval interval, Date returnDate) {
		super();
		this.interval = interval;
		this.returnDate = DateUtil.ignoringSeconds(returnDate);
	}

	public boolean isValidInterval() {
		
		long intervalInMinutes = interval.getBasicIntervalInMinutes();
		Date outDate = interval.getOutDate();
		
		long restInterval = DateUtil.getIntervalInMinutes(outDate, returnDate);
		
		if(intervalInMinutes <= NO_REST_NEEDED) {
		
			return true;
			
		} else if(intervalInMinutes > NO_REST_NEEDED && intervalInMinutes < REST_TIME_OF_A_HOUR_NEEDED) {
			
			return restInterval >= QUARTER_OF_HOUR;
			
		} else {
			
			return restInterval >= HOUR;
		}
		
	}
	
}
