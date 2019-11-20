package com.marcos.punchclock.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class PunchClock {
	
	@Id
	private Integer id;
	
	@NotNull
	private Date date;
	
	@ManyToOne
	private Employee employee;

	public PunchClock() {}
	
	public PunchClock(Integer id, @NotNull Date date, Employee employee) {
		this.id = id;
		this.date = date;
		this.employee = employee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	
	
	
}
