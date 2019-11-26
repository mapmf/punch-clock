package com.marcos.punchclock.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marcos.punchclock.model.util.EmployeeHoursFormatterUtil;
import com.marcos.punchclock.model.util.PunchClockRestTime;
import com.marcos.punchclock.model.util.WorkingPunchClockInterval;

/**
 * 
 * This class groups a set of employee punch clock of a day 
 * 
 * @author Marcos André
 *
 */

@Entity
public class EmployeeWorkDay implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	private Employee employee;

	private Date createdAt;

	@OneToMany(mappedBy = "employeeWorkDay")
	private List<PunchClock> punchClocks = new ArrayList<PunchClock>();

	public EmployeeWorkDay() {
	}

	public EmployeeWorkDay(Integer id, Employee employee, Date createdAt) {

		this.id = id;
		this.employee = employee;
		this.createdAt = createdAt;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@JsonFormat(pattern = "MM/dd/yyyy HH:mm")
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<PunchClock> getPunchClocks() {
		return punchClocks;
	}

	public void setPunchClocks(List<PunchClock> punchClocks) {
		this.punchClocks = punchClocks;
	}

	public String getWorkingHours() {
		
		double workingMinutes = calculateWorkingMinutes();
		
		return EmployeeHoursFormatterUtil.format(workingMinutes);
	}
	
	public double calculateWorkingMinutes() {

		punchClocks.sort(Comparator.comparing(PunchClock::getDate));

		double workingMinutes = 0;

		for (int i = 0; i < punchClocks.size() - 1; i = i + 2) {

			PunchClock inPunchClock = punchClocks.get(i);
			PunchClock outPunchClock = punchClocks.get(i + 1);

			WorkingPunchClockInterval interval = new WorkingPunchClockInterval(inPunchClock, outPunchClock);

			workingMinutes += interval.calculateMinutes();

		}

		return workingMinutes;
	}

	public boolean getMissingAnyRestTime() {

		punchClocks.sort(Comparator.comparing(PunchClock::getDate));

		for (int i = 0; i < punchClocks.size() - 2; i = i + 3) {

			PunchClock inPunchClock = punchClocks.get(i);
			PunchClock outPunchClock = punchClocks.get(i + 1);
			PunchClock returnPunchClock = punchClocks.get(i + 2);

			WorkingPunchClockInterval workingInterval = new WorkingPunchClockInterval(inPunchClock, outPunchClock);

			PunchClockRestTime restInterval = new PunchClockRestTime(workingInterval,
					returnPunchClock.getDate());

			if (!restInterval.isValidInterval()) {
				return true;
			}
		}

		return false;

	}

}
