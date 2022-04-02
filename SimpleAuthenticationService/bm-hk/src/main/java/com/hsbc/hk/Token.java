package com.hsbc.hk;

 

import java.time.LocalDateTime;

 

/**

* This interface represents the authentication token used in the system.

*

* Note, the token is supposed to be sent around the internet, and therefore is not a safe place to store sensitive data.

* It shouldn't be large either.

*/

public interface Token {

    /**

     * Get the expiry time for this token. After this time it should not be valid and no operations with it in the system

     * should succeed.

     *

     * @return the expiry time

     */

    LocalDateTime getExpiryTime();

}


