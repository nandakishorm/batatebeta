package com.kishor.batatebeta.core.service;

import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.exception.BatateException;

import java.util.List;

/**
 * Created by Nandakishor on 8/12/2015.
 */
public interface UserService extends AbstractService<User> {
    public User findByUsername(String username) throws BatateException;
    public List<User> findAll();
}
