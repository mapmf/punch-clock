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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.marcos.punchclock.model.enums.Profile;

@Entity
public class Employee {
	@Id
	private String pis;

	@NotEmpty(message = "Name is required")
	private String name;

	@JsonIgnore
	@Length(min = 6, message = "Password must have 6 characters at least")
	@NotNull(message = "Password is required")
	private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "profiles")
	private Set<Integer> profileIds = new HashSet<Integer>();

	@JsonIgnore
	@OneToMany(mappedBy = "employee")
	private List<EmployeeWorkDay> employeeWorkDays = new ArrayList<EmployeeWorkDay>();

	public Employee() {
		addProfile(Profile.USER);
	}

	public Employee(String pis, String name, String password) {
		
		this.pis = pis;
		this.name = name;
		this.password = password;
		
		addProfile(Profile.USER);
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

	public Set<Profile> getProfiles() {

		Set<Profile> profiles = new HashSet<Profile>();
		
		for (int profileId : this.profileIds) {
			profiles.add(Profile.toEnum(profileId));
		}
		//return profiles.stream().map(p -> Profile.toEnum(p)).collect(Collectors.toSet());
		
		return profiles;
	}
}
