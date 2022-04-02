package com.hsbc.hk;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

public class TokenImpl implements Token {
	
	private UUID key;
	private LocalDateTime expiryTime;
	private static final int HASH_PRIME = 31;
	
	public TokenImpl(long validPeriodInSec) {
		this.key = UUID.randomUUID();
		this.expiryTime = LocalDateTime.now().plus(validPeriodInSec, ChronoUnit.SECONDS);
	}

	public UUID getKey() {
		return this.key;
	}
	
	@Override
	public LocalDateTime getExpiryTime() {
		return this.expiryTime;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(key.toString()).append("|").append(expiryTime).toString();		
	}

	@Override
	public boolean equals(Object obj) {
		if (obj != null) {
			if (obj instanceof TokenImpl) {
				TokenImpl t = (TokenImpl)obj;
				return t==this ? true :  key.equals(t.getKey()) && expiryTime.equals(t.getExpiryTime());
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	
	@Override
	public int hashCode() {
		return HASH_PRIME * key.hashCode() + expiryTime.hashCode();
	}

}
