package org.demo.application.userservice.service;

import org.demo.application.userservice.controller.model.RestAddress;
import org.demo.application.userservice.controller.model.RestUser;
import org.demo.application.userservice.controller.model.RestUserDetails;
import org.demo.application.userservice.db.UserDbService;
import org.demo.application.userservice.model.Address;
import org.demo.application.userservice.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserService {

    private UserDbService userDbService;
    private UserObjectMapper userObjectMapper;
    private AddressObjectMapper addressObjectMapper;

    public Long save(RestUser user) {
        log.info("Starting saving user {} ",user);

        User convertedUser = userObjectMapper.covertTo(user);
        log.debug("Converted rest user {} to {}");

        Long userId = userDbService.save(convertedUser);
        log.info("Saved user {} with id {}",user,userId);

        return userId;
    }

    public void updateAddress(Long id, RestAddress addressToConvert) {
        log.info("Starting adding address {] to user with id {} ",addressToConvert,id);

        Address convertedAddress = addressObjectMapper.covertTo(addressToConvert);
        log.debug("Converted rest address {} to {} ",addressToConvert,convertedAddress);

        User user = userDbService.getUser(id);
        log.debug("Found following user {} for requested id {} ",user,id);

        user.getAddresses().add(convertedAddress);
        userDbService.update(id, user);
        log.info("Added address to user with id {}",id);
    }

    public Set<RestUserDetails> getUsers() {
        log.debug("Starting getting all users");
        Set<User> users = userDbService.getUsers();
        log.debug("Got following users from DB {} ",users);

        Set<RestUserDetails> result = users.stream()
                .map(this::createRestUserDetails)
                .collect(Collectors.toSet());

        log.debug("Prepared following mapped users and their addresses collection {}",result);
        return result;
    }

    private RestUserDetails createRestUserDetails(final User user) {
        return RestUserDetails.builder()
                .addresses(addressObjectMapper.covertFrom(user.getAddresses()))
                .user(userObjectMapper.covertFrom(user))
                .build();
    }
}
