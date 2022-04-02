package com.hsbc.hk;

import java.util.Objects;

public class RoleImpl implements Role {

	private String name;
	private static final int HASH_PRIME = 31;
	private static final int CLASSHASH = RoleImpl.class.hashCode();
	public RoleImpl(String name) {
		Objects.requireNonNull(name, "name cannot be null");
		this.name = name;
	}
	
	@Override
	public String name() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return new StringBuilder("RoleImpl[").append(name).append("]").toString();
	}
	
	@Override
	public boolean equals(Object o) {
		if (o != null) {
			if (o instanceof RoleImpl) {
				RoleImpl r = (RoleImpl)o;
				return name.equals(r.name());
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
