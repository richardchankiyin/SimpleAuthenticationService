package com.hsbc.hk;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
	private Map<User,UserDetail> userMap;
	
	public UserManager() {
		userMap = new ConcurrentHashMap<>();	
	}
	
	public User createUser(String name, String password) {
		Objects.requireNonNull(password, "password cannot be null");
		User u = new UserImpl(name);
		if (userMap.containsKey(u)) {
			// user has been created
			throw new RuntimeException("User: " + name + " was created previously");
		} else {
			userMap.put(u, new UserDetail(password));
			return u;
		}
	}
	
	public UserDetail deleteUser(User user) {
		if (user != null) {
			UserDetail ud = userMap.remove(user);
			if (ud == null) {
				throw new RuntimeException("User: " + user.name() + " could not be found");
			}
			return ud;
		} else {
			return null;
		}		
	}
	
	public Optional<User> findUser(String name) {
		User u = new UserImpl(name); 
		if (userMap.containsKey(u)) {
			return Optional.of(u);
		} else {
			return Optional.empty();
		}
	}
	
	public void addRoleToUser(Role role, User user) {
		if (user != null && role != null) {
			if (userMap.containsKey(user)) {
				UserDetail ud = userMap.get(user);
				ud.addRoleToUser(role);
			}
		}
	}
	
	public Set<Role> getAllRoles(User user) {
		UserDetail ud = userMap.get(user);
		if (ud != null) {
			return ud.getAllRoles();
		} else {
			return null;
		}
	}
	
}
