package com.hsbc.hk;

public class UserImpl implements User {
	
	private String name;
	private static final int HASH_PRIME = 31;
	private static final int CLASSHASH = UserImpl.class.hashCode();
	public UserImpl(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("UserImpl[").append(name).append("]").toString();
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public boolean equals(Object o) {
		if (o != null) {
			if (o instanceof UserImpl) {
				UserImpl u = (UserImpl)o;
				return name.equals(u.name());
			} else {
				return false;
			}
		} else {
			return false;
		}		
	}
	
	@Override
	public int hashCode() {
		return HASH_PRIME * CLASSHASH + name.hashCode();
	}
}
