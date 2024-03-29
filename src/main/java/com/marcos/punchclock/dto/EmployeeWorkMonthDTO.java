package com.marcos.punchclock.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcos.punchclock.model.EmployeeWorkDay;
import com.marcos.punchclock.model.util.EmployeeHoursFormatterUtil;

/**
 * 
 * This class is a data transfer object that groups 
 * EmployeeWorkDay objects by month
 * 
 * @see EmployeeWorkDay
 * 
 * @author Marcos André
 */

public class EmployeeWorkMonthDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "MM/yyyy")
	private Date monthDate;

	private List<EmployeeWorkDay> employeeWorkDays;

	public EmployeeWorkMonthDTO(Date monthDate, List<EmployeeWorkDay> employeeWorkDays) {

		this.employeeWorkDays = employeeWorkDays;
		this.monthDate = monthDate;

	}

	public String getTotalWorkingHours() {

		double totalWorkingMinutes = calculateTotalWorkingMinutes();

		return EmployeeHoursFormatterUtil.format(totalWorkingMinutes);
	}

	public double calculateTotalWorkingMinutes() {

		double total = 0;

		for (EmployeeWorkDay employeeWorkDay : employeeWorkDays) {
			total += employeeWorkDay.calculateWorkingMinutes();
		}

		return total;
	}

	public List<EmployeeWorkDay> getEmployeeWorkDays() {
		return employeeWorkDays;
	}

	public Date getMonthDate() {
		return monthDate;
	}

	public void setMonthDate(Date monthDate) {
		this.monthDate = monthDate;
	}

}
