package org.demo.application.userservice.db.repository;

import org.demo.application.userservice.db.model.UserEntity;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    Long addUser(UserEntity userEntity);

    Optional<UserEntity> getUser(Long id);

    Set<UserEntity> getUsers();
}
