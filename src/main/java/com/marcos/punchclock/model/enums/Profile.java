package com.marcos.punchclock.model.enums;

public enum Profile {
	USER(1, "ROLE_USER"),
	ADMIN(2, "ROLE_ADMIN");
	
	private Integer id;
	private String role;
	
	private Profile(Integer id, String role) {
		this.id = id;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public static Profile toEnum(Integer id) {
		
		if(id == null) {
			return null;
		}
		
		for (Profile profile : Profile.values()) {
			
			if(profile.getId() == id) {
				return profile;
			}
		}
		
		throw new IllegalArgumentException("Invalid id: " + id);
	}
}
