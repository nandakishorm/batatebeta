package com.kishor.batatebeta.core.service.impl;

import com.google.common.base.Preconditions;
import com.kishor.batatebeta.core.dictionary.MessageDictionary;
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
        Preconditions.checkNotNull(entity, MessageDictionary.OBJECT_IS_NULL);
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) throws BatateException {
        Preconditions.checkNotNull(entity, MessageDictionary.OBJECT_IS_NULL);
        Preconditions.checkNotNull(entity.getUid(), MessageDictionary.OBJECT_ID_IS_NULL);
        return userRepository.save(entity);
    }

    @Override
    public User findByUid(String uid) throws BatateException {
        Preconditions.checkNotNull(uid, MessageDictionary.VALUE_IS_NULL);
        User user = userRepository.findByUid(uid);
        Preconditions.checkNotNull(user, MessageDictionary.ENTITY_WAS_NOT_FOUND);
        return user;
    }

    @Override
    public User findByUsername(String username) throws BatateException {
        Preconditions.checkNotNull(username, MessageDictionary.VALUE_IS_NULL);
        User user = userRepository.findByUserName(username);
        return user;
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }

}
