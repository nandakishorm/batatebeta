package com.kishor.batatebeta.core.repository;

import com.kishor.batatebeta.core.dictionary.Role;
import com.kishor.batatebeta.core.dictionary.Status;
import com.kishor.batatebeta.core.domain.User;

import java.util.List;

/**
 * Created by Nandakishor on 8/12/2015.
 */
public interface UserRepository extends BaseRepository<User, Long> {
    public User findByUid(String uid);

    public User findByUserName(String userName);

    public List<User> findByRole(Role role);

    public List<User> findByStatusNot(Status status);
}
