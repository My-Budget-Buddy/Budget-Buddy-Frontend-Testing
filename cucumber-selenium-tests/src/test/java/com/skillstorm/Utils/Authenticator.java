package com.skillstorm.Utils;

import com.skillstorm.Utils.UserData.User;
import com.skillstorm.Utils.UserData.UserType;

public class Authenticator {
//#region Static Fields

    public static final String USERNAME_PERSISTENT = "persistent@email.com";
    public static final String PASSWORD_PERSISTENT = "persistent";

    public static final String USERNAME_NONPERSIST = "nonpersistent@email.com";
    public static final String PASSWORD_NONPERSIST = "nonpersistent";

    public static final String USERNAME_WRONG = "wrongformat!";
    public static final String PASSWORD_WRONG = "";

//#endregion

    private static User userPersistent = new User(UserType.PERSISTANT, USERNAME_PERSISTENT, PASSWORD_PERSISTENT);
    private static User userNonpersistent = new User(UserType.NONPERSISTANT, USERNAME_NONPERSIST, PASSWORD_NONPERSIST);
    private static User userWrong = new User(UserType.WRONG, USERNAME_WRONG, PASSWORD_WRONG);

    /**
     * Performs the login process for the user of passed type.
     * @param type  Type of user to login.
     * @apiNote     UserType.WRONG should always fail. Additionally, this method does NOT sign up UserType.NONPERSISTENT.
     */
    public static void login(UserType type){
        User user = getFromType(type);

        // Perform login functions.
    }

    /**
     * Performs the signup process for the user of passed type.
     * @param type  Type of user to sign up.
     * @apiNote     UserType.WRONG should always fail, and UserType.PERSISTENT should only work once for a given test suite.
     */
    public static void signup(UserType type){
        User user = getFromType(type);

        // Perform signup functions.
    }

    /**
     * Retrieves the correct user.
     * @param type  Type of user to retrieve.
     * @return      User.
     */
    private static User getFromType(UserType type){
        switch (type) {
            case PERSISTANT:
                return userPersistent;
            case NONPERSISTANT:
                return userNonpersistent;
            case WRONG:
                return userWrong;
            default:    // Should never happen.
                throw new IllegalArgumentException("Parameter 'type' as '" + type.toString() + "' not found.");
        }
    }
}