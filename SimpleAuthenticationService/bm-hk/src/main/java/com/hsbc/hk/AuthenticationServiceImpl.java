package com.hsbc.hk;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

public class AuthenticationServiceImpl implements AuthenticationService {

	@Override
	public User createUser(String name, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public Optional<User> findUser(String name) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub

	}

	@Override
	public boolean isValid(Token token) {
		if (token != null) {
			return !LocalDateTime.now().isBefore(token.getExpiryTime());
		} else {
			return false;
		}
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
