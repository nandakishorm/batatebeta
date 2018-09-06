package com.kishor.batatebeta.core.domain;

import com.kishor.batatebeta.core.dictionary.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * Created by Nandakishor on 8/11/2015.
 */

@Data
@NoArgsConstructor
@ToString
@Entity
public class User extends BaseEntity {

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "fullName", nullable = false)
    private String fullName;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "role", nullable = false, columnDefinition = "ENUM('ADMINISTRATOR', 'USER') default 'USER'")
    @Enumerated(EnumType.STRING)
    @Basic(fetch = FetchType.EAGER)
    private Role role;
}
