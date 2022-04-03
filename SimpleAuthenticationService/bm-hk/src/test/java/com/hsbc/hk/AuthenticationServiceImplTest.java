package com.hsbc.hk;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class AuthenticationServiceImplTest {

	@Mock
	UserManager userManager;
	@Mock
	RoleManager roleManager;
	@Mock
	TokenManager tokenManager;
	@Mock
	AuthenticationManager authenticationManager;
	
	private AuthenticationService authenticationService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		authenticationService = new AuthenticationServiceImpl(userManager, roleManager, tokenManager, authenticationManager);
	}
	
	@Test
	public void testCreateUser() {
		String user = "user";
		String password = "password";
		User userObj = new UserImpl(user);
		Mockito.when(userManager.createUser(user,password)).thenReturn(userObj);
		assertEquals(userObj, authenticationService.createUser(user, password));
	}
	
	@Test
	public void testDeleteUser() {
		String user = "user";
		String password = "password";
		UserDetail userDetail = new UserDetail(password);
		User userObj = new UserImpl(user);
		Mockito.when(userManager.deleteUser(userObj)).thenReturn(userDetail);
		authenticationService.deleteUser(userObj);
	}
	
	@Test
	public void testFindUser() {
		String user = "user";
		User userObj = new UserImpl(user);
		String user2 = "user2";
		Mockito.when(userManager.findUser(user)).thenReturn(Optional.of(userObj));
		Mockito.when(userManager.findUser(user2)).thenReturn(Optional.empty());
		assertEquals(Optional.of(userObj), authenticationService.findUser(user));
		assertTrue(authenticationService.findUser(user2).isEmpty());
	}
	
	@Test
	public void testCreateRole() {
		String role = "role";
		Role roleObj = new RoleImpl(role);
		Mockito.when(roleManager.createRole(role)).thenReturn(roleObj);
		assertEquals(roleObj, authenticationService.createRole(role));
	}

	@Test
	public void testFindRole() {
		String role = "role";
		Role roleObj = new RoleImpl(role);
		String role2 = "role2";
		Mockito.when(roleManager.findRole(role)).thenReturn(Optional.of(roleObj));
		Mockito.when(roleManager.findRole(role2)).thenReturn(Optional.empty());
		assertEquals(Optional.of(roleObj), authenticationService.findRole(role));
		assertTrue(authenticationService.findRole(role2).isEmpty());
	}
	
}
