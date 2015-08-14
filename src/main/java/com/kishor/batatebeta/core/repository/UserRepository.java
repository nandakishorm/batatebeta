package com.kishor.batatebeta.core.repository;

import com.kishor.batatebeta.core.domain.User;

/**
 * Created by Nandakishor on 8/12/2015.
 */
public interface UserRepository extends BaseRepository<User, Long> {

    public User findByUserName(String userName);
}
