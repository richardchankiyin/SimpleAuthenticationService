package com.hsbc.hk;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class TokenManager {
	private Set<Token> tokenSet;
	private int validTimeInSec;
	public TokenManager(int validTimeInSec) {
		this.validTimeInSec = validTimeInSec;
		this.tokenSet = new HashSet<>();
	}
	
	public Token createToken() {
		Token t = new TokenImpl(validTimeInSec);
		tokenSet.add(t);
		return t;
	}
	
	/**
	 * This method will check the token still be found.
	 * The token can be expired because it is just 
	 * an enquiry method
	 * @return
	 */
	public boolean isTokenStillHere(Token t) {
		return tokenSet.contains(t);
	}
	
	public void invalidate(Token token) {
		tokenSet.remove(token);
	}
	
	public boolean isValid(Token token) {
		if (token != null) {
			if (tokenSet.contains(token)) {
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
