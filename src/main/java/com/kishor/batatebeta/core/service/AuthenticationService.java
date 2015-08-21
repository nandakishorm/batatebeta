package com.kishor.batatebeta.core.service;

import com.kishor.batatebeta.core.domain.User;

/**
 * Created by Nandakishor on 8/20/2015.
 */
public interface AuthenticationService {

    public User findByUsername(String username);

    public User whoAmI();
}
