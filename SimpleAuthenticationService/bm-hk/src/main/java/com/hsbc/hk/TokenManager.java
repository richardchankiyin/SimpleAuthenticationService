package com.hsbc.hk;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TokenManager {
	private Map<Token, User> tokenMap;
	private int validTimeInSec;
	public TokenManager(int validTimeInSec) {
		this.validTimeInSec = validTimeInSec;
		this.tokenMap = new HashMap<>();
	}
	
	public Token createToken(User user) {
		Objects.requireNonNull(user, "cannot be null");
		Token t = new TokenImpl(validTimeInSec);
		tokenMap.put(t, user);
		return t;
	}
	
	public User getTokenUser(Token token) {
		Objects.requireNonNull(token, "cannot be null");
		return tokenMap.get(token);
	}
	
	/**
	 * This method will check the token still be found.
	 * The token can be expired because it is just 
	 * an enquiry method
	 * @return
	 */
	public boolean isTokenStillHere(Token t) {
		return tokenMap.containsKey(t);
	}
	
	public void invalidate(Token token) {
		tokenMap.remove(token);
	}
	
	public boolean isValid(Token token) {
		if (token != null) {
			if (tokenMap.containsKey(token)) {
				boolean isExpired = !LocalDateTime.now().isBefore(token.getExpiryTime());
				if (isExpired) {
					// invalid the token
					invalidate(token);
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
}
