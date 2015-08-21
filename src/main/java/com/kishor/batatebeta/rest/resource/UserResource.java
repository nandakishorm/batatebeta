package com.kishor.batatebeta.rest.resource;

/**
 * Created by Nandakishor on 8/14/2015.
 */
public class UserResource extends BaseResource {

    private String userName;

    private String password;

    private String fullName;

    private String role;

    public UserResource()
    {}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
