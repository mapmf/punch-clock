package com.marcos.punchclock.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class PunchClock implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@JsonFormat(pattern = "MM/dd/yyyy HH:mm")
	@NotNull
	private Date date;
	
	@JsonIgnore
	@ManyToOne
	private Employee employee;
	
	@JsonIgnore
	@ManyToOne
	private EmployeeWorkDay employeeWorkDay;
	

	public PunchClock() {}
	
	public PunchClock(Integer id, @NotNull Date date, Employee employee, EmployeeWorkDay employeeWorkDay) {
		this.id = id;
		this.date = date;
		this.employee = employee;
		this.employeeWorkDay = employeeWorkDay;
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
	
	public EmployeeWorkDay getEmployeeWorkDay() {
		return employeeWorkDay;
	}

	public void setEmployeeWorkDay(EmployeeWorkDay employeeWorkDay) {
		this.employeeWorkDay = employeeWorkDay;
	}
	
	
	
	
	
}
