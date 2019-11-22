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
	
	public static Date getBeginOfMonth(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(date);

		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		
		return calendar.getTime();
	}
	
	public static Date getEndOfMonth(Date date) {
		
		Calendar calendar = Calendar.getInstance();
		
		Date beginOfMonth = getBeginOfMonth(date);
		
		calendar.setTime(beginOfMonth);
		
		calendar.add(Calendar.MONTH, 1);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		
		return calendar.getTime();
	}
	
	public static double getIntervalInHours(Date date1, Date date2) {
		
		double intervalInMiliseconds = getIntervalInMiliseconds(date1, date2);
		
		double hourInMiliseconds = 60*60*1000;
		
		return intervalInMiliseconds/hourInMiliseconds;
		
	}
	
	public static double getIntervalInMiliseconds(Date date1, Date date2) {
		
		return date2.getTime() - date1.getTime();
		
	}
}
