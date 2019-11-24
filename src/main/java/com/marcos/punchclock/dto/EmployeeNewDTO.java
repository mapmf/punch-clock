package com.marcos.punchclock.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.marcos.punchclock.model.Employee;

import br.com.caelum.stella.bean.validation.NIT;

/**
 *
 * A data transfer object for Employee class
 * used to create a new employee
 *  
 * @author Marcos André
 */

public class EmployeeNewDTO implements Serializable{

	private static final long serialVersionUID = 1L;

	@NIT
	@NotEmpty(message = "PIS is required")
	private String id;
	
	@NotNull(message = "Name is required")
	@Length(min = 3, max=200)
	private String name;
	
	@NotEmpty(message = "Password is required")
	private String password;
	
	public EmployeeNewDTO() {}
	
	public EmployeeNewDTO(Employee employee) {

		this.id = employee.getId();
		this.name = employee.getName();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
