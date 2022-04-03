package com.hsbc.hk;

import java.util.Objects;
import java.util.Optional;

public class AuthenticationManager {
	private UserManager userManager;
	private TokenManager tokenManager;
	public AuthenticationManager(UserManager userManager, TokenManager tokenManager) {
		this.userManager = userManager;
		this.tokenManager = tokenManager;
	}
	public Token authenticate(String username, String password, String salt) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		Optional<User> userOpt = userManager.findUser(username);
		if (userOpt.isEmpty()) {
			throw new RuntimeException("User: " + username + " not exist");			
		}
		User user = userOpt.get();
		if (!password.equals(userManager.getPassword(user))) {
			throw new RuntimeException("Authentication failed");
		} else {
			return tokenManager.createToken(user);
		}
	}
}
