package com.hsbc.hk;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TokenManagerTest {

	private TokenManager tmgr;
	private User user;
	@Before
	public void setup() {
		tmgr = new TokenManager(30 * 60);
		user = new UserImpl("user");
	}
	
	@Test
	public void testCreateToken() {
		Token t = tmgr.createToken(user);
		assertNotNull(t);
		assertTrue(tmgr.isTokenStillHere(t));
	}
	
	@Test
	public void testGetTokenUser() {
		Token t = tmgr.createToken(user);
		assertEquals(user, tmgr.getTokenUser(t));
	}
	
	@Test
	public void testInvalidateToken() {
		Token t = tmgr.createToken(user);
		tmgr.invalidate(t);
		assertFalse(tmgr.isTokenStillHere(t));
	}
	
	@Test
	public void testIsValidStillHereNotExpired() {
		Token t = tmgr.createToken(user);
		assertTrue(tmgr.isTokenStillHere(t));
		assertTrue(tmgr.isValid(t));
	}
	
	@Test
	public void testIsValidAfterInvalidate() {
		Token t = tmgr.createToken(user);
		tmgr.invalidate(t);
		assertFalse(tmgr.isTokenStillHere(t));
		assertFalse(tmgr.isValid(t));
	}
	
	@Test
	public void testIsValidAfterExpired() throws Exception{
		TokenManager tmgr1sec = new TokenManager(1);
		Token t = tmgr1sec.createToken(user);
		Thread.sleep(1000);
		assertTrue(tmgr1sec.isTokenStillHere(t));
		assertFalse(tmgr1sec.isValid(t));
		assertFalse(tmgr1sec.isTokenStillHere(t));
	}

}
