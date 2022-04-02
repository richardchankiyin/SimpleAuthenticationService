package com.hsbc.hk;

import static org.junit.Assert.*;

import org.junit.Test;

public class RoleImplTest {
	
	@Test(expected=RuntimeException.class)
	public void testNameNull() {
		new RoleImpl(null);
	}

	@Test
	public void testName() {
		RoleImpl r1 = new RoleImpl("r1");
		RoleImpl r2 = new RoleImpl("r2");
		assertEquals("r1", r1.name());
		assertEquals("r2", r2.name());
	}

	@Test
	public void testEquals() {
		RoleImpl r1 = new RoleImpl("r1");
		RoleImpl r1Mirror = new RoleImpl("r1");
		RoleImpl r2 = new RoleImpl("r2");
		
		assertEquals(r1, r1Mirror);
		assertNotEquals(r1, r2);
		assertNotEquals(r1Mirror, r2);
		assertNotEquals(r1, null);
		assertNotEquals(r2, null);
	}
	
	@Test
	public void testHashCodes() {
		RoleImpl r1 = new RoleImpl("r1");
		RoleImpl r1Mirror = new RoleImpl("r1");
		RoleImpl r2 = new RoleImpl("r2");
		
		assertTrue(r1.hashCode() == r1Mirror.hashCode());
		assertTrue(r1.hashCode() != r2.hashCode());
	}
}
