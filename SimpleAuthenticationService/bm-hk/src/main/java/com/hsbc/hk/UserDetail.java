package com.hsbc.hk;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class UserDetail {

	private String password;
	private Set<Role> roles;
	
	public UserDetail(String password) {
		this.password = password;
		roles = new HashSet<>();
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public Set<Role> getAllRoles() {
		return Collections.unmodifiableSet(roles);
	}
	
	public void addRoleToUser(Role role) {
		Objects.requireNonNull(role, "cannot be null");
		if (!roles.add(role)) {
			throw new RuntimeException("Role: " + role.name() + " has been added previously");
		}
	}
	
	public void deleteRoleFromUser(Role role) {
		Objects.requireNonNull(role, "cannot be null");
		roles.remove(role);
	}
	
}
