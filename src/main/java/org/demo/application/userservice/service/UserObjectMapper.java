package org.demo.application.userservice.service;

import com.google.common.collect.Sets;
import org.demo.application.userservice.controller.model.RestUser;
import org.demo.application.userservice.model.User;
import org.springframework.stereotype.Component;

@Component
class UserObjectMapper {

    User covertTo(final RestUser user) {
        return new User(user.getName(),user.getSurname(),user.getAge(), Sets.newHashSet());
    }

    RestUser covertFrom(User user) {
        return new RestUser(user.getName(),user.getSurname(),user.getAge());
    }
}
