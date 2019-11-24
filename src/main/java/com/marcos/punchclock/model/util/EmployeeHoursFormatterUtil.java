package com.marcos.punchclock.model.util;

/**
 * This class formats a given hours to a more friendly string 
 * 
 * Example:
 * 
 * EmployeeHoursFormatterUtil.format(0.25) will return 0:15
 * because 15 minutes are equivalent a 0.25 hours 
 * 	
 * @author Marcos André
 */

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
