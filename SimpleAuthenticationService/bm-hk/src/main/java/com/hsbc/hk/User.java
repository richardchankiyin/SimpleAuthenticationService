package com.hsbc.hk;

 

 

/**

* This interface represents the user for the system.

*

* Note, the object might be sent around the internet, and therefore is not a safe place to store sensitive data.

*/

public interface User {

    String name();

}
