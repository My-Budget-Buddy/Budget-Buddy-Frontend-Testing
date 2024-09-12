/**
 * By: Aaron Huggins
 * 
 * Description:
 *      Defines the different user types used in testing.
 */

package com.skillstorm.Utilities.UserData;

public enum UserType {
    PERSISTANT,     // Should be created before all tests and destroyed after all tests, login information should be kept the same.
    NONPERSISTANT,  // Should be created and destroyed for individual tests.
    WRONG           // Incorrect signup/login information.
}
