package com.kishor.batatebeta.core.service.impl;

import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.repository.UserRepository;
import com.kishor.batatebeta.core.service.UserService;
import com.kishor.batatebeta.exception.BatateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Nandakishor on 8/12/2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User create(User entity) throws BatateException {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) throws BatateException {
        return userRepository.save(entity);
    }

    @Override
    public User findByUid(String uid) throws BatateException {
        return null;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

}
