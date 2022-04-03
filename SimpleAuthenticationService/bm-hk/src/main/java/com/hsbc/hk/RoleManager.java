package com.hsbc.hk;

import java.util.HashSet;
import java.util.Set;

public class RoleManager {
	private Set<Role> roles;
	
	public RoleManager() {
		roles = new HashSet<>();
	}
	
	public void createRole(String name) {
		RoleImpl role = new RoleImpl(name);
		if (roles.contains(role)) {
			throw new RuntimeException("Role: " + name + " was created previously");
		} else {
			roles.add(role);
		}
	}
}
