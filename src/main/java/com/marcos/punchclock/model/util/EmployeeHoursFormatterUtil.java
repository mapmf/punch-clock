package com.marcos.punchclock.model.util;

public class EmployeeHoursFormatterUtil {

	public static String format(Double hours) {

		StringBuilder sb = new StringBuilder();

		int fullHours = hours.intValue();
		
		sb.append(fullHours);
		sb.append(":");
		
		Double minutes = (hours - fullHours) * 60;
		
		int fullMinutes = minutes.intValue();
		
		if(fullMinutes < 10) {
			sb.append(0);	
		}
		
		sb.append(fullMinutes);
		
		return sb.toString();

	}
}
