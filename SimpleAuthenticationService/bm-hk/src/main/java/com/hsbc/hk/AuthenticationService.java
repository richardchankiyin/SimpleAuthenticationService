package com.hsbc.hk;

 

import java.util.Optional;

import java.util.Set;

 

/**

* Authentication service interface.

*/

public interface AuthenticationService {

    /**

     * Add a new user with the given name and password

     * @param name

     * @param password

     * @return the newly created user

     * @throws RuntimeException if unable to create a user

     */

    User createUser(String name, String password);

 

    /**

     * Delete the given user

     * @param user

     * @throws RuntimeException if there is no such user

     */

    void deleteUser(User user);

 

    /**

     * Find a user with the given name

     * @param name

     * @return optional user (none, if not found)

     */

    Optional<User> findUser(String name);

 

    /**

     * Add a new role with the given name

     * @param name

     * @return the new rule object

     * @throws RuntimeException if unable to create the role

     */

    Role createRole(String name);

 

    /**

     * Find a role with the given name

     * @param name

     * @return optional role (none, if not found)

     */

    Optional<Role> findRole(String name);

 

    /**

     * Delete the given role

     * @param role

     * @throws RuntimeException if there is no such role

     */

    void deleteRole(Role role);

 

    /**

     * Add the given user to the given role

     * @param role

     * @param user

     * @throws RuntimeException if unable to perform the operation

     */

    void addRoleToUser(Role role, User user);

 

    /**

     * Authenticate the user with the password/salt.

     *

     * Note, this method doesn't pretend to be too secure.  Bonus question - how would you fix it?

     * Write your suggestions in the README.

     *

     * @param username

     * @param password

     * @param salt

     * @return a new authentication token, which is valid for a fixed period of time (i.e. 30min)

     * @throws RuntimeException if unable to authenticate

     */

    Token authenticate(String username, String password, String salt);

 

    /**

     * After calling this function the token will no longer be valid, even if it hasn't expired yet

     * @param token

     * @throws RuntimeException if unable to perform the operation

     */

    void invalidate(Token token);

 

    /**

     * Checks if the token is valid, for example not expired yet. It should never throw

     * @param token

     * @return true if valid and false otherwise

     */

    boolean isValid(Token token);

 

    /**

     * Check if the user, identified by the given token, is a member of the given role

     * @param token

     * @param role

     * @return true or false regarding the membership

     * @throws RuntimeException if unable to perform the operation. I.e. if the token is invalid

     */

    boolean checkRole(Token token, Role role);

 

    /**

     * Get the roles to which the user, identified by the given token, belongs

     * @param token

     * @return the collection of all roles for the user

     * @throws RuntimeException if unable to perform the operation. I.e. if the token is invalid

     */

    Set<Role> getAllRoles(Token token);

 

    /**

     * Return the list of user names of all the users that are members of the given role.

     * @param role

     * @return the user names

     * @throws RuntimeException if unable to perform the operation

     */

    Set<String> getMembers(Role role);

}
