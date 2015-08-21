package com.kishor.batatebeta.core.service.impl;

import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.repository.UserRepository;
import com.kishor.batatebeta.core.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

/**
 * Created by Nandakishor on 8/20/2015.
 */

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUserName(username);
    }

    @Override
    public User whoAmI() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByUserName(userDetails.getUsername());
    }
}
