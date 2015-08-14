package com.kishor.batatebeta.rest.controller;

import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.service.UserService;
import com.kishor.batatebeta.exception.BatateException;
import com.kishor.batatebeta.rest.resource.UserResource;
import com.kishor.batatebeta.rest.resource.assembler.UserAssembler;
import com.kishor.batatebeta.rest.resource.assembler.UserResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Nandakishor on 8/14/2015.
 */

@Controller
public class UserController extends AbstractController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserResource> create(@RequestBody UserResource userResource) throws BatateException {
        User user = userAssembler.toEntity(userResource);
        user = userService.create(user);
        userResource = userResourceAssembler.toResource(user);
        return new ResponseEntity<>(userResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @ResponseBody
    public ResponseEntity<UserResource> update(@RequestBody UserResource userResource) throws BatateException {
        User user = userAssembler.toEntity(userResource);
        user = userService.update(user);
        userResource = userResourceAssembler.toResource(user);
        return new ResponseEntity<>(userResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<UserResource>> findByStatus() throws BatateException {
        List<User> users = userService.findAll();
        List<UserResource> userResources = userResourceAssembler.toResources(users);
        return new ResponseEntity<>(userResources, HttpStatus.OK);
    }
}