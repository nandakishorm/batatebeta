package com.kishor.batatebeta.core.service.impl;

import com.google.common.base.Preconditions;
import com.kishor.batatebeta.core.dictionary.MessageDictionary;
import com.kishor.batatebeta.core.dictionary.Role;
import com.kishor.batatebeta.core.dictionary.Status;
import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.repository.UserRepository;
import com.kishor.batatebeta.core.service.UserService;
import com.kishor.batatebeta.exception.BatateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
        Preconditions.checkArgument(!entity.getUserName().isEmpty(), MessageDictionary.VALUE_IS_EMPTY);
        Preconditions.checkArgument(!entity.getFullName().isEmpty(), MessageDictionary.VALUE_IS_EMPTY);
        Preconditions.checkArgument(!entity.getPassword().isEmpty(), MessageDictionary.VALUE_IS_EMPTY);
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) throws BatateException {
        Preconditions.checkNotNull(entity, MessageDictionary.OBJECT_IS_NULL);
        Preconditions.checkNotNull(entity.getUid(), MessageDictionary.OBJECT_ID_IS_NULL);
        entity.setId(findByUid(entity.getUid()).getId());
        return userRepository.save(entity);
    }

    public User delete(User entity) throws BatateException {
        Preconditions.checkNotNull(entity, MessageDictionary.VALUE_IS_NULL);
        Preconditions.checkNotNull(entity.getUid(), MessageDictionary.OBJECT_ID_IS_NULL);
        entity = findByUid(entity.getUid());
        entity.setStatus(Status.DELETED);
        return userRepository.save(entity);
    }

    @Override
    public User findByUid(String uid) throws BatateException {
        Preconditions.checkNotNull(uid, MessageDictionary.VALUE_IS_NULL);
        Preconditions.checkArgument(!uid.isEmpty(), MessageDictionary.VALUE_IS_EMPTY);
        User user = userRepository.findByUid(uid);
        Preconditions.checkNotNull(user, MessageDictionary.ENTITY_WAS_NOT_FOUND);
        return user;
    }

    @Override
    @Cacheable(value = "userCache")
    public User findByUsername(String username) throws BatateException {
        Preconditions.checkNotNull(username, MessageDictionary.VALUE_IS_NULL);
        Preconditions.checkArgument(!username.isEmpty(), MessageDictionary.VALUE_IS_EMPTY);
        User user = userRepository.findByUserName(username);
        return user;
    }

    @Override
    public List<User> findByRole(Role role) throws BatateException {
        Preconditions.checkNotNull(role, MessageDictionary.VALUE_IS_NULL);
        return userRepository.findByRole(role);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findByStatusNot(Status.DELETED);
    }

}
