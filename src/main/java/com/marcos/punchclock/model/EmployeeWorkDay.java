package com.marcos.punchclock.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class EmployeeWorkDay implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Employee employee;
	
	private Date createdAt;
	
	@OneToMany(mappedBy = "employeeWorkDay")
	private List<PunchClock> punchClocks = new ArrayList<PunchClock>();

	public EmployeeWorkDay() {}
	
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
	
	
	
	
}
