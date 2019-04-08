package org.demo.application.userservice.db;

import org.demo.application.userservice.db.mapper.EntityMapper;
import org.demo.application.userservice.db.model.UserEntity;
import org.demo.application.userservice.db.repository.UserRepository;
import org.demo.application.userservice.exception.NotExistUserException;
import org.demo.application.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class UserDbService {

    private UserRepository userRepository;
    private EntityMapper entityMapper;

    public Long save(User user) {
        log.debug("Staring adding new user {}",user);

        UserEntity userEntity = entityMapper.convertTo(user);
        log.debug("User {} was converted to {} entity ",user,userEntity);

        return userRepository.addUser(userEntity);
    }

    public void update(Long userId, User user) {
        log.debug("Staring updating user with id {} {} ",userId,user);

        UserEntity userEntity = entityMapper.convertTo(user);
        log.debug("User {} was converted to {} entity ",user,userEntity);

        userRepository.updateUser(userId,userEntity);
        log.debug("User with id {} was updated successfully",userId);
    }

    public User getUser(Long userId) {
        log.debug("Getting user with id {} {} ",userId);
        return userRepository.getUser(userId).map(entityMapper::convertFrom).orElseThrow(() ->
                new NotExistUserException("User with id " + userId + " does not exists in system"));
    }

    public Set<User> getUsers() {
        log.debug("Getting all existing users");
        return userRepository.getUsers().stream()
                .map(entityMapper::convertFrom)
                .collect(Collectors.toSet());
    }
}
