package com.hsbc.hk;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

public class UserManagerTest {

	private UserManager userMgr;
	private User existingUser;
	private String existingUserName;
	private String existingUserPassword;
	@Before
	public void setup() {
		userMgr = new UserManager();
		existingUserName = "existingName";
		existingUser = new UserImpl(existingUserName);
		existingUserPassword = "existingPwd";
		userMgr.createUser(existingUserName, existingUserPassword);
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
		userMgr.deleteUser(existingUser);
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
}
