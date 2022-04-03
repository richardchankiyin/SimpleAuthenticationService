package com.hsbc.hk;


import java.util.Optional;
import java.util.Set;

public class AuthenticationServiceImpl implements AuthenticationService {
	private UserManager userManager;
	private RoleManager roleManager;
	private TokenManager tokenManager;
	private AuthenticationManager authenticationManager;
	
	public AuthenticationServiceImpl(UserManager userManager, RoleManager roleManager, TokenManager tokenManager, AuthenticationManager authenticationManager) {
		this.userManager = userManager;
		this.roleManager = roleManager;
		this.tokenManager = tokenManager;
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public User createUser(String name, String password) {
		return userManager.createUser(name, password);
	}

	@Override
	public void deleteUser(User user) {
		userManager.deleteUser(user);
	}

	@Override
	public Optional<User> findUser(String name) {
		return userManager.findUser(name);
	}

	@Override
	public Role createRole(String name) {
		return roleManager.createRole(name);
	}

	@Override
	public Optional<Role> findRole(String name) {
		return roleManager.findRole(name);
	}

	@Override
	public void deleteRole(Role role) {
		Set<String> userNames = roleManager.deleteRole(role);
		userNames.forEach(n -> {userManager.deleteRoleFromUser(role, new UserImpl(n));});
	}

	@Override
	public void addRoleToUser(Role role, User user) {
		userManager.addRoleToUser(role, user);
		roleManager.addRoleToUser(role, user);
	}

	@Override
	public Token authenticate(String username, String password, String salt) {
		return authenticationManager.authenticate(username, password, salt);
	}

	@Override
	public void invalidate(Token token) {
		tokenManager.invalidate(token);
	}

	@Override
	public boolean isValid(Token token) {
		return tokenManager.isValid(token);
	}
	
	protected User checkToken(Token token) {
		if (!isValid(token)) {
			throw new RuntimeException("token is invalid");
		}
		return tokenManager.getTokenUser(token);
	}

	@Override
	public boolean checkRole(Token token, Role role) {
		User user = checkToken(token);
		return userManager.checkRole(user, role);
	}

	@Override
	public Set<Role> getAllRoles(Token token) {
		User user = checkToken(token);
		return userManager.getAllRoles(user);
	}

	@Override
	public Set<String> getMembers(Role role) {
		return roleManager.getMembers(role);
	}

}
