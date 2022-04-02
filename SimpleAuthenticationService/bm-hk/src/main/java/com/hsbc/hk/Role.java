package com.hsbc.hk;

 

/**

* This interface represents the role used in the system.

*

* Note, the object might be sent around the internet, and therefore is not a safe place to store sensitive data.

*/

public interface Role {

    String name();

}
