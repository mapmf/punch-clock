package com.marcos.punchclock.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getBeginOfDay(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
	
	public static Date getEndOfDay(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);
		calendar.set(Calendar.HOUR, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		
		return calendar.getTime();
	}
	
	public static double getIntervalInHours(Date date1, Date date2) {
		
		long intervalInMiliseconds = getIntervalInMiliseconds(date1, date2);
		
		long hourInMiliseconds = 60*60*1000;
		
		return intervalInMiliseconds/hourInMiliseconds;
		
	}
	
	public static long getIntervalInMiliseconds(Date date1, Date date2) {
		
		return date2.getTime() - date1.getTime();
		
	}
}
