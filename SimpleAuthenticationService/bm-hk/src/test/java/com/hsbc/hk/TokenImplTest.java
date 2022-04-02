package com.hsbc.hk;

import static org.junit.Assert.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.junit.Test;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TokenImplTest {
	private static final Logger logger;
	static {
		String path = TokenImplTest.class.getClassLoader()
                .getResource("loggingtest.properties")
                .getFile();
		System.setProperty("java.util.logging.config.file", path);
		logger = Logger.getLogger(TokenImplTest.class.getName());
	}
	@Test
	public void testGetExpiryTime30Mins() {
		final int timeadjustment = 1000;
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDateTime expectedExpiryTime = LocalDateTime.now().plus((30 * 60 * 1000) + timeadjustment, ChronoUnit.MILLIS);
		TokenImpl t = new TokenImpl(30 * 60);
		LocalDateTime expiryTime = t.getExpiryTime(); 
		assertTrue(currentTime.isBefore(expiryTime));
		logger.log(Level.FINE, "expiryTime: {0}", expiryTime);
		logger.log(Level.FINE, "expected expiryTime: " + expectedExpiryTime);
		assertTrue(expiryTime.isBefore(expectedExpiryTime));
	}

	@Test
	public void testEquals() {
		TokenImpl t = new TokenImpl(30 * 60);
		assertEquals(t,t);
		TokenImpl t2 = new TokenImpl(30 * 60);
		assertNotEquals(t,t2);
	}
	
}
