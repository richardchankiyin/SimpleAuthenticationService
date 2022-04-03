package com.hsbc.hk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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
		Mockito.verify(userManager, Mockito.times(1)).deleteUser(userObj);
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
	
	@Test
	public void testDeleteRole() {
		String user = "user";
		String user2 = "user2";
		User userObj = new UserImpl(user);
		User userObj2 = new UserImpl(user2);
		String role = "role";
		Role roleObj = new RoleImpl(role);
		Set<String> userSet = new HashSet<>();
		userSet.add(user);
		userSet.add(user2);

		Mockito.when(roleManager.deleteRole(roleObj)).thenReturn(userSet);
		authenticationService.deleteRole(roleObj);
		Mockito.verify(userManager, Mockito.times(1)).deleteRoleFromUser(roleObj, userObj);
		Mockito.verify(userManager, Mockito.times(1)).deleteRoleFromUser(roleObj, userObj2);
	}
	
	@Test
	public void testAddRoleToUser() {
		String user = "user";
		User userObj = new UserImpl(user);
		String role = "role";
		Role roleObj = new RoleImpl(role);
		
		authenticationService.addRoleToUser(roleObj, userObj);
		Mockito.verify(userManager, Mockito.times(1)).addRoleToUser(roleObj, userObj);
		Mockito.verify(roleManager, Mockito.times(1)).addRoleToUser(roleObj, userObj);
	}
	
	@Test
	public void testAuthenticateSuccess() {
		String user = "user";
		String password = "password";
		Token token = Mockito.mock(Token.class);
		Mockito.when(authenticationManager.authenticate(user, password, "")).thenReturn(token);
		assertEquals(token, authenticationService.authenticate(user, password, ""));
	}
	
	@Test(expected=RuntimeException.class)
	public void testAuthenticationFailed() {
		String user = "user";
		String password = "password";
		Mockito.when(authenticationManager.authenticate(user, password, "")).thenThrow(new RuntimeException());
		authenticationService.authenticate(user, password, "");
	}
	
	@Test
	public void testInvalidate() {
		Token token = Mockito.mock(Token.class);
		authenticationService.invalidate(token);
		Mockito.verify(tokenManager, Mockito.times(1)).invalidate(token);
	}
	
	@Test
	public void testIsValid() {
		Token token = Mockito.mock(Token.class);
		Token token2 = Mockito.mock(Token.class);
		Mockito.when(tokenManager.isValid(token)).thenReturn(true);
		Mockito.when(tokenManager.isValid(token2)).thenReturn(false);
		assertTrue(authenticationService.isValid(token));
		assertFalse(authenticationService.isValid(token2));
	}
	
	@Test
	public void testCheckRoleSuccess() {
		Token token = Mockito.mock(Token.class);
		Mockito.when(tokenManager.isValid(token)).thenReturn(true);
		String user = "user";
		User userObj = new UserImpl(user);
		Mockito.when(tokenManager.getTokenUser(token)).thenReturn(userObj);
		String role = "role";
		Role roleObj = new RoleImpl(role);
		String role2 = "role2";
		Role roleObj2 = new RoleImpl(role2);
		Mockito.when(userManager.checkRole(userObj, roleObj)).thenReturn(true);
		Mockito.when(userManager.checkRole(userObj, roleObj2)).thenReturn(false);
		assertTrue(authenticationService.checkRole(token, roleObj));
		assertFalse(authenticationService.checkRole(token, roleObj2));
	}
	
	@Test(expected=RuntimeException.class)
	public void testCheckRoleFailed() {
		Token token = Mockito.mock(Token.class);
		Mockito.when(tokenManager.isValid(token)).thenReturn(false);
		String role = "role";
		Role roleObj = new RoleImpl(role);
		authenticationService.checkRole(token, roleObj);		
	}
	
	@Test
	public void testGetAllRolesSuccess() {
		Token token = Mockito.mock(Token.class);
		Mockito.when(tokenManager.isValid(token)).thenReturn(true);
		String user = "user";
		User userObj = new UserImpl(user);
		Mockito.when(tokenManager.getTokenUser(token)).thenReturn(userObj);
		String role = "role";
		Role roleObj = new RoleImpl(role);
		String role2 = "role2";
		Role roleObj2 = new RoleImpl(role2);
		Set<Role> roles = new HashSet<>();
		roles.add(roleObj);
		roles.add(roleObj2);
		Mockito.when(tokenManager.getTokenUser(token)).thenReturn(userObj);
		Mockito.when(userManager.getAllRoles(userObj)).thenReturn(roles);
		authenticationService.getAllRoles(token);
	}
	
	@Test(expected=RuntimeException.class)
	public void testGetAllRolesFailed() {
		Token token = Mockito.mock(Token.class);
		Mockito.when(tokenManager.isValid(token)).thenReturn(false);
		authenticationService.getAllRoles(token);		
	}
	
	@Test
	public void testGetMembers() {
		String user = "user";
		String user2 = "user2";
		String role = "role";
		Role roleObj = new RoleImpl(role);
		Set<String> userSet = new HashSet<>();
		userSet.add(user);
		userSet.add(user2);
		
		Mockito.when(roleManager.getMembers(roleObj)).thenReturn(userSet);
		assertEquals(userSet, authenticationService.getMembers(roleObj));
	}
	
}
