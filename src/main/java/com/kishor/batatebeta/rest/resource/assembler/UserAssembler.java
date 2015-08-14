package com.kishor.batatebeta.rest.resource.assembler;

import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.rest.resource.UserResource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Nandakishor on 8/14/2015.
 */

@Component
public class UserAssembler extends EntityAssembler<UserResource, User> {

    @Autowired
    private Mapper mapper;

    public User toEntity(UserResource userResource){
        return mapper.map(userResource, User.class);
    }
}
