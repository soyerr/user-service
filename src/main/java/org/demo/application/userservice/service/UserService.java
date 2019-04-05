package org.demo.application.userservice.service;

import com.google.common.collect.Sets;

import org.demo.application.userservice.db.UserDbService;
import org.demo.application.userservice.model.RestUser;
import org.demo.application.userservice.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDbService userDbService;

    public UserService(UserDbService userDbService){

        this.userDbService = userDbService;
    }

    public Long addUser(RestUser user){
       return userDbService.addUser(covertTo(user));
    }

    private User covertTo(final RestUser user) {
        return new User(user.getName(),user.getSurname(),user.getAge(), Sets.newHashSet());
    }

}
