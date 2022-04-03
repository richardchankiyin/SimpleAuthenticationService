package com.hsbc.hk;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
public class AuthenticationManagerTest {
	
	@Mock
	UserManager userManager;
	
	@Mock
	TokenManager tokenManager;
	
	@Mock
	Token token;
	private AuthenticationManager authenticationManager;
	
	private String user, user2;
	private String password, password2;
	private User userObj;
	
	
	@Before
	public void setup() {
		user = "user";
		user2 = "user2";
		userObj = new UserImpl(user);
		password = "password";
		password2 = "password2";
		MockitoAnnotations.initMocks(this);
		Mockito.when(userManager.findUser(user)).thenReturn(Optional.of(userObj));
		Mockito.when(userManager.findUser(user2)).thenReturn(Optional.empty());
		Mockito.when(userManager.getPassword(userObj)).thenReturn(password);
		Mockito.when(tokenManager.createToken(userObj)).thenReturn(token);
		authenticationManager = new AuthenticationManager(userManager, tokenManager);
	}

	@Test
	public void testAuthenticateSuccess() {
		assertEquals(token, authenticationManager.authenticate(user, password, ""));
	}
	
	@Test
	public void testAuthenticationFailedDuetoWrongPassword() {
		try {
			authenticationManager.authenticate(user, password2, "");
			fail("should not reach here");
		}
		catch (RuntimeException re) {
			assertEquals("Authentication failed", re.getMessage());
		}		
	}
	
	@Test
	public void testAuthenticationFailedDuetoNoSuchUser() {
		try {
			authenticationManager.authenticate(user2, password2, "");
			fail("should not reach here");
		}
		catch (RuntimeException re) {
			assertEquals("User: " + user2 + " not exist", re.getMessage());
		}	
	}

}
