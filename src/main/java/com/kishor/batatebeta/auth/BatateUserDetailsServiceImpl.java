package com.kishor.batatebeta.auth;

import com.kishor.batatebeta.core.dictionary.Status;
import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nandakishor on 8/20/2015.
 */
@Service("batateUserDetailsServiceImpl")
public class BatateUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = authenticationService.findByUsername(username);
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

        org.springframework.security.core.userdetails.User userDetails =
                new org.springframework.security.core.userdetails.User(username, user.getPassword(), user.getStatus().equals(Status.ACTIVE), true, true, true, authorities);

        return userDetails;
    }
}
