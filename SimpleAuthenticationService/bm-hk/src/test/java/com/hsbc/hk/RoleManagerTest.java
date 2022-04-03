package com.hsbc.hk;

import static org.junit.Assert.*;

import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class RoleManagerTest {

	private RoleManager roleManager;
	private Role existingRole;
	private String existingRoleName;
	private User existingUser, existingUser2;
	private String existingUserName, existingUserName2;
	
	@Before
	public void setup() {
		roleManager = new RoleManager();
		existingRoleName = "existingRole";
		existingRole = roleManager.createRole(existingRoleName);
		existingUserName = "existingUser";
		existingUserName2 = "existingUser2";
		existingUser = new UserImpl(existingUserName);
		existingUser2 = new UserImpl(existingUserName2);
		roleManager.addRoleToUser(existingRole, existingUser);
		roleManager.addRoleToUser(existingRole, existingUser2);
	}
	
	@Test
	public void testCreateRoleSuccess() {
		Role r = roleManager.createRole("testRole");
		assertEquals("testRole", r.name());
	}
	
	@Test(expected=RuntimeException.class)
	public void testCreateRoleExisted() {
		roleManager.createRole(existingRoleName);
	}
	
	@Test
	public void testFindRoleSuccess() {
		Optional<Role> result = roleManager.findRole(existingRoleName);
		assertTrue(result.isPresent());
		assertEquals(existingRole, result.get());
	}
	
	@Test
	public void testFindRoleNotExist() {
		Optional<Role> result = roleManager.findRole("testRole");
		assertTrue(result.isEmpty());		
	}
	
	@Test
	public void testGetMembersSuccess() {
		Set<String> result = roleManager.getMembers(existingRole);
		assertTrue(result.contains(existingUserName) && result.contains(existingUserName2) && result.size() == 2);		
	}

}
