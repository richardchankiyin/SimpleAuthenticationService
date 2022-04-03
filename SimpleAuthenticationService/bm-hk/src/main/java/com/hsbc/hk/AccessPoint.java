package com.hsbc.hk;

import jdk.jshell.spi.ExecutionControl;

public class AccessPoint {

    public AuthenticationService createInstance() throws ExecutionControl.NotImplementedException {
        // implement this method to return a concrete instance of @see AuthenticationService interface implementation
    	UserManager userManager = new UserManager();
    	RoleManager roleManager = new RoleManager();
    	TokenManager tokenManager = new TokenManager(30 * 60);
    	AuthenticationManager authenticationManager = new AuthenticationManager(userManager, tokenManager);
    	return new AuthenticationServiceImpl(userManager, roleManager, tokenManager, authenticationManager);

    }

}
