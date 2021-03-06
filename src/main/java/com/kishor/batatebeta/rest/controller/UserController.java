package com.kishor.batatebeta.rest.controller;

import com.kishor.batatebeta.core.dictionary.Role;
import com.kishor.batatebeta.core.domain.User;
import com.kishor.batatebeta.core.service.UserService;
import com.kishor.batatebeta.exception.BatateException;
import com.kishor.batatebeta.rest.resource.UserResource;
import com.kishor.batatebeta.rest.resource.assembler.UserAssembler;
import com.kishor.batatebeta.rest.resource.assembler.UserResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Nandakishor on 8/14/2015.
 */

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserAssembler userAssembler;

    @Autowired
    private UserResourceAssembler userResourceAssembler;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @PreAuthorize("hasAnyRole('USER', 'ADMINISTRATOR')")
    @ResponseBody
    public ResponseEntity<UserResource> create(@RequestBody UserResource userResource) throws BatateException {
        User user = userAssembler.toEntity(userResource);
        user = userService.create(user);
        userResource = userResourceAssembler.toResource(user);
        return new ResponseEntity<>(userResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('USER', 'ADMINISTRATOR')")
    @ResponseBody
    public ResponseEntity<UserResource> update(@RequestBody UserResource userResource) throws BatateException {
        User user = userAssembler.toEntity(userResource);
        user = userService.update(user);
        userResource = userResourceAssembler.toResource(user);
        return new ResponseEntity<>(userResource, HttpStatus.OK);
    }

    /*Test method to get an error page*//*
    @RequestMapping(value = "/users/error", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ROLE_KISHOR')")
    @ResponseBody
    public String getErrorPage() throws BatateException {
        return "pagenotfound";
    }*/

    /* TEST METHOD TO TEST CONTROLLER ADVICE CLASS and it is working fine */
    @RequestMapping(value = "/controlleradvice", method = RequestMethod.PUT)
    @PreAuthorize("hasAnyRole('USER', 'ADMINISTRATOR')")
    @ResponseBody
    public ResponseEntity<UserResource> checkControllerAdvice() throws BatateException {
        throw new BatateException("BATATE TEST EXCEPTION:::::CONTROLLER ADVICE CLASS IS CALLED.");
    }

    @RequestMapping(value = "/users/all", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR', 'ROLE_KISHOR')")
    @ResponseBody
    public ResponseEntity<List<UserResource>> findAll() throws BatateException {
        List<User> users = userService.findAll();
        List<UserResource> userResources = userResourceAssembler.toResources(users);
        return new ResponseEntity<>(userResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('USER')")
    @ResponseBody
    public ResponseEntity<List<UserResource>> findUsers() throws BatateException {
        List<User> users = userService.findByRole(Role.USER);
        List<UserResource> userResources = userResourceAssembler.toResources(users);
        return new ResponseEntity<>(userResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('ADMINISTRATOR')")
    @ResponseBody
    public ResponseEntity<List<UserResource>> findAdmins() throws BatateException {
        List<User> users = userService.findByRole(Role.ADMINISTRATOR);
        List<UserResource> userResources = userResourceAssembler.toResources(users);
        return new ResponseEntity<>(userResources, HttpStatus.OK);
    }

    @RequestMapping(value = "/me", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserResource> findMe() throws BatateException {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findByUsername(userDetails.getUsername());
        UserResource userResource = userResourceAssembler.toResource(user);
        return new ResponseEntity<>(userResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/user/logout", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session != null)
            session.invalidate();
    }
}
