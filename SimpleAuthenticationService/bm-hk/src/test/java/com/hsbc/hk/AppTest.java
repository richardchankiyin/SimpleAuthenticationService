package com.hsbc.hk;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
	private static final Logger logger;
	static {
		String path = TokenImplTest.class.getClassLoader()
                .getResource("loggingtest.properties")
                .getFile();
		System.setProperty("java.util.logging.config.file", path);
		logger = Logger.getLogger(AppTest.class.getName());
	}
	

    @Test
    public void testUserAndRoleCreation() throws Exception
    {
    	logger.info("Running testUserAndRoleCreation...");
        AccessPoint ap = new AccessPoint();
        AuthenticationService as = ap.createInstance();
        String user1 = "user1";
        String password1 = "password1";
        
        User userObj1 = as.createUser(user1, password1);
        logger.log(Level.INFO, "user {0} created", userObj1);
        String role1 = "role1";
        Role roleObj1 = as.createRole(role1);
        logger.log(Level.INFO, "role {0} created", roleObj1);
        as.addRoleToUser(roleObj1, userObj1);
        
        assertNotNull(userObj1);
        assertNotNull(roleObj1);
    }
    
    @Test
    public void testAuthenticateAndRoleChecking() throws Exception {
    	logger.info("Running testAuthenticateAndRoleChecking...");
    	AccessPoint ap = new AccessPoint();
        AuthenticationService as = ap.createInstance();
        String user1 = "user1";
        String password1 = "password1";
        
        User userObj1 = as.createUser(user1, password1);
        logger.log(Level.INFO, "user {0} created", userObj1);
        String role1 = "role1";
        Role roleObj1 = as.createRole(role1);
        logger.log(Level.INFO, "role {0} created", roleObj1);
        as.addRoleToUser(roleObj1, userObj1);
        
        Token token1 = as.authenticate(user1, password1, "");
        logger.log(Level.INFO, "user {0} login with token: {1}", new Object[] {userObj1, token1});
        assertTrue(as.checkRole(token1, roleObj1));
        assertTrue(as.getAllRoles(token1).contains(roleObj1));
        assertEquals(Optional.of(roleObj1), as.findRole(role1));
        assertEquals(Optional.of(userObj1), as.findUser(user1));
        
        as.invalidate(token1);
        
        try {
        	as.checkRole(token1, roleObj1);
        	fail("should not reach here");
        } catch (RuntimeException re) {
        	assertEquals("token is invalid", re.getMessage());
        }
    }
    
    @Test
    public void testMaintainUserAndRole() throws Exception{
    	logger.info("Running testMaintainUserAndRole...");
    	AccessPoint ap = new AccessPoint();
        AuthenticationService as = ap.createInstance();
        String user1 = "user1";
        String password1 = "password1";
        
        User userObj1 = as.createUser(user1, password1);
        logger.log(Level.INFO, "user {0} created", userObj1);
        String role1 = "role1";
        Role roleObj1 = as.createRole(role1);
        logger.log(Level.INFO, "role {0} created", roleObj1);
        as.addRoleToUser(roleObj1, userObj1);
        
        Token token1 = as.authenticate(user1, password1, "");
        logger.log(Level.INFO, "role {0} created", roleObj1);
        assertTrue(as.checkRole(token1, roleObj1));
        
        as.deleteRole(roleObj1);
        assertTrue(as.findRole(role1).isEmpty());
        assertFalse(as.checkRole(token1, roleObj1));
    }
}
