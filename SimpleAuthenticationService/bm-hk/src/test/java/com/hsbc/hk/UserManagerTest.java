package com.hsbc.hk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class UserManagerTest {

	private UserManager userMgr;
	private User existingUser;
	private String existingUserName;
	private String existingUserPassword;
	private Role existingRole, existingRole2;
	private String existingRoleName, existingRoleName2;
	@Before
	public void setup() {
		userMgr = new UserManager();
		existingUserName = "existingName";
		existingUser = new UserImpl(existingUserName);
		existingUserPassword = "existingPwd";
		userMgr.createUser(existingUserName, existingUserPassword);
		existingRoleName = "existingRole";
		existingRole = new RoleImpl(existingRoleName);
		existingRoleName2 = "existingRole2";
		existingRole2 = new RoleImpl(existingRoleName2);
		userMgr.addRoleToUser(existingRole, existingUser);
		userMgr.addRoleToUser(existingRole2, existingUser);
	}
	
	@Test
	public void testCreateUserSuccess() {
		User user = userMgr.createUser("testuser", "passwd");
		assertNotNull(user);
		assertEquals("testuser", user.name());
	}
	
	@Test(expected=RuntimeException.class)
	public void testCreateUserNameNull() {
		userMgr.createUser(null, "passwd");
	}
	
	@Test(expected=RuntimeException.class)
	public void testCreateUserPasswordNull() {
		userMgr.createUser("testuser", null);
	}

	@Test(expected=RuntimeException.class)
	public void testCreateUserExisted() {
		userMgr.createUser(existingUserName, "passwd");
	}
	
	@Test
	public void testDeleteUserSuccess() {
		UserDetail ud = userMgr.deleteUser(existingUser);
		assertEquals(existingUserPassword, ud.getPassword());
		Set<Role> result = ud.getAllRoles();
		assertTrue(result.contains(existingRole) && result.contains(existingRole2) && result.size() == 2);
	}
	
	@Test(expected=RuntimeException.class)
	public void testDeleteUserNotExist() {
		User u = new UserImpl("testuser");
		userMgr.deleteUser(u);
	}
	
	@Test
	public void testFindUserExist() {
		Optional<User> result = userMgr.findUser(existingUserName);
		assertTrue(result.isPresent());
		assertEquals(existingUser, result.get());
	}
	
	@Test
	public void testFindUserNotExist() {
		Optional<User> result = userMgr.findUser("testuser");
		assertTrue(result.isEmpty());
	}
	
	public void testGetAllRoles() {
		Set<Role> result = userMgr.getAllRoles(existingUser);
		assertTrue(result.contains(existingRole) && result.contains(existingRole2) && result.size() == 2);
	}
}
