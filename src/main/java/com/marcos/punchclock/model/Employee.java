package com.marcos.punchclock.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

@Entity
public class Employee{
	@Id
	private String pis;
	
	@NotEmpty(message = "Name is required")
	private String name;
	
	@OneToMany(mappedBy = "employee")
	private  List<PunchClock> punchClocks = new ArrayList<PunchClock>();

	public Employee() {}
	
	public Employee(String pis, String name) {
		this.pis = pis;
		this.name = name;
	}

	public String getPis() {
		return pis;
	}

	public void setPis(String pis) {
		this.pis = pis;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	public List<PunchClock> getPunchClocks(){
		return this.punchClocks;
	}
}
