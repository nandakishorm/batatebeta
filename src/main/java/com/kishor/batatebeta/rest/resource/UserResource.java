package com.kishor.batatebeta.rest.resource;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * User DTO class | Created by Nandakishor on 8/14/2015.
 * @author Nandakishor.m
 */

@Data
@NoArgsConstructor
@ToString
public class UserResource extends BaseResource {

    private String userName;

    private String password;

    private String fullName;

    private String role;

    private String email;
}
