package com.kishor.batatebeta.core.service;

import com.kishor.batatebeta.core.domain.User;

import java.util.List;

/**
 * Created by Nandakishor on 8/12/2015.
 */
public interface UserService extends AbstractService<User> {
    public List<User> findAll();
}
