package com.marcos.punchclock.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcos.punchclock.model.enums.Profile;

/**
 * This class represents employees
 * 
 * @author Marcos André
 *
 */


@Entity
public class Employee {
	@Id
	private String id;

	private String name;

	@JsonIgnore
	private String password;

	@JsonIgnore
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "profiles")
	private Set<Integer> profileIds = new HashSet<Integer>();

	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<EmployeeWorkDay> employeeWorkDays = new ArrayList<EmployeeWorkDay>();

	public Employee() {
		addProfile(Profile.USER);
	}

	public Employee(String id, String name, String password) {
		
		this.id = id;
		this.name = name;
		this.password = password;
		
		addProfile(Profile.USER);
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

	public List<EmployeeWorkDay> getEmployeeWorkDays() {
		return this.employeeWorkDays;
	}

	public void addProfile(Profile profile) {
		profileIds.add(profile.getId());
	}

	@JsonIgnore
	public Set<Profile> getProfiles() {

		Set<Profile> profiles = new HashSet<Profile>();
		
		for (int profileId : this.profileIds) {
			profiles.add(Profile.toEnum(profileId));
		}
		
		return profiles;
	}
}
