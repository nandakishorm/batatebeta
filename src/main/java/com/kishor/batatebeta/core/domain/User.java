package com.kishor.batatebeta.core.domain;

import com.kishor.batatebeta.core.dictionary.Role;

import javax.persistence.*;

/**
 * Created by Nandakishor on 8/11/2015.
 */

@Entity
public class User extends BaseEntity {

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "role", nullable = false, columnDefinition = "ENUM('ADMINISTRATOR', 'USER') default 'USER'")
    @Enumerated(EnumType.STRING)
    @Basic(fetch = FetchType.EAGER)
    private Role role;

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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
