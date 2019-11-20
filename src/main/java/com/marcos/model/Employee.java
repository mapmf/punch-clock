package com.marcos.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Employee{
	@Id
	private String pis;
	
	@NotEmpty(message = "Name is required")
	private String name;

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
}
