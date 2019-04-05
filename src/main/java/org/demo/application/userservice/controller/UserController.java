package org.demo.application.userservice.controller;

import org.demo.application.userservice.model.RestAddress;
import org.demo.application.userservice.model.RestUser;
import org.demo.application.userservice.service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    private UserController(UserService userService) {

        this.userService = userService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public Map<RestUser, RestAddress> getUsers(){
//        Set<User> users = userService.getUsers();

        return null;
    }

    @PostMapping(path = "/user",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createUser(@RequestBody RestUser user){
        userService.addUser(user);
    }

    @PostMapping(path = "/user/{id}/address",consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public void createAddress(@PathVariable("id") Long id, @RequestBody RestAddress address){

    }
}
