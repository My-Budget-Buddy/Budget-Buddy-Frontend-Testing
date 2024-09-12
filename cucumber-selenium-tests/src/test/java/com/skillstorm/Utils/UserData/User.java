/**
 * By: Aaron Huggins
 * 
 * Description:
 *      This class represents a user's data for the purposes of logging
 *      in.
 */

package com.skillstorm.Utils.UserData;

public class User {
    private UserType type;
    private String username;
    private String password;

    public User(UserType type, String username, String password){
        this.type = type;
        this.username = username;
        this.password = password;
    }

//#region Getters/Setters
    
    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//#endregion    
}
