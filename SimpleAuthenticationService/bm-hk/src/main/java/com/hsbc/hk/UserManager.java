package com.hsbc.hk;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class UserManager {
	private Map<User,String> userMap;
	
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
			userMap.put(u, password);
			return u;
		}
	}
	
	public void deleteUser(User user) {
		if (user != null) {
			if (userMap.remove(user) == null) {
				throw new RuntimeException("User: " + user.name() + " could not be found");
			}
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
	
}
