package com.kishor.batatebeta.rest.resource.assembler;

import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.rest.controller.UserController;
import com.kishor.batatebeta.rest.resource.UserResource;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nandakishor on 8/14/2015.
 */

@Component
public class UserResourceAssembler extends ResourceAssemblerSupport<User, UserResource> {

    @Autowired
    private Mapper mapper;

    public UserResourceAssembler() {
        super(UserController.class, UserResource.class);
    }

    @Override
    public UserResource toResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }

    @Override
    public List<UserResource> toResources(Iterable<? extends User> entities) {
        List<UserResource> userResources = new ArrayList<>();
        for(User user : entities) {
            UserResource  userResource = mapper.map(user, UserResource.class);
            userResources.add(userResource);
        }
        return userResources;
    }
}
