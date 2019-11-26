package com.marcos.punchclock.model.util;

/**
 * This class formats a given minutes to hours in friendly format
 * 
 * Example:
 * 
 * EmployeeHoursFormatterUtil.format(420) will return 7:00
 * because 420 minutes is equivalent to 7 hours 
 * 	
 * @author Marcos André
 */

public class EmployeeHoursFormatterUtil {

	public static String format(Double minutes) {

		StringBuilder sb = new StringBuilder();

		Double fullHours = minutes/60;
		
		sb.append(fullHours.intValue());
		sb.append(":");
		
		Double rest = minutes % 60;
		
		if(rest == 0) {
			
			sb.append("00");
			
		} else {
			
			if(rest < 10) {
				
				sb.append(0);	
			}
		
			sb.append(rest.intValue());
		}

		
		return sb.toString();

	}
}
