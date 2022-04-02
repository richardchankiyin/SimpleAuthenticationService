package com.hsbc.hk;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserImplTest {

	@Test
	public void testName() {
		UserImpl u1 = new UserImpl("u1");
		UserImpl u2 = new UserImpl("u2");
		assertEquals("u1", u1.name());
		assertEquals("u2", u2.name());
	}
	
	@Test
	public void testEquals() {
		UserImpl u1 = new UserImpl("u1");
		UserImpl u1Mirror = new UserImpl("u1");
		UserImpl u2 = new UserImpl("u2");
		
		assertEquals(u1, u1Mirror);
		assertNotEquals(u1, u2);
		assertNotEquals(u1Mirror, u2);
		assertNotEquals(u1, null);
		assertNotEquals(u2, null);
	}
	
	@Test
	public void testHashCodes() {
		UserImpl u1 = new UserImpl("u1");
		UserImpl u1Mirror = new UserImpl("u1");
		UserImpl u2 = new UserImpl("u2");
		
		assertTrue(u1.hashCode() == u1Mirror.hashCode());
		assertTrue(u1.hashCode() != u2.hashCode());
	}

}
