package com.hsbc.hk;


import java.util.Optional;
import java.util.Set;

public class AuthenticationServiceImpl implements AuthenticationService {
	private UserManager userManager;
	private TokenManager tokenManager;
	
	public AuthenticationServiceImpl(UserManager userManager, TokenManager tokenManager) {
		this.userManager = userManager;
		this.tokenManager = tokenManager;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Role> findRole(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRole(Role role) {
		// TODO Auto-generated method stub

	}

	@Override
	public void addRoleToUser(Role role, User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public Token authenticate(String username, String password, String salt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void invalidate(Token token) {
		tokenManager.invalidate(token);
	}

	@Override
	public boolean isValid(Token token) {
		return tokenManager.isValid(token);
	}

	@Override
	public boolean checkRole(Token token, Role role) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Set<Role> getAllRoles(Token token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<String> getMembers(Role role) {
		// TODO Auto-generated method stub
		return null;
	}

}
