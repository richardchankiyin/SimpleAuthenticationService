package com.hsbc.hk;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class RoleManager {
	private Map<Role, Set<String>> roleMap;
	
	public RoleManager() {
		roleMap = new HashMap<>();
	}
	
	public Role createRole(String name) {
		RoleImpl role = new RoleImpl(name);
		if (roleMap.containsKey(role)) {
			throw new RuntimeException("Role: " + name + " was created previously");
		} else {			
			roleMap.put(role, new HashSet<String>());
			return role;
		}
	}
	
	public Optional<Role> findRole(String name) {
		RoleImpl role = new RoleImpl(name);
		if (roleMap.containsKey(role)) {
			return Optional.of(role);
		} else {
			return Optional.empty();
		}
	}
	
	public void addRoleToUser(Role role, User user) {
		if (user != null && role != null) {
			if (roleMap.containsKey(role)) {
				Set<String> memberSet = roleMap.get(role);
				memberSet.add(user.name());
			}
		}
	}
	
	public Set<String> getMembers(Role role) {
		return roleMap.get(role);
	}
}
