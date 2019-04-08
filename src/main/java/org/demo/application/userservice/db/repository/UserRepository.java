package org.demo.application.userservice.db.repository;

import org.demo.application.userservice.db.model.UserEntity;

import java.util.Optional;
import java.util.Set;

public interface UserRepository {

    Long addUser(final UserEntity userEntity);

    void updateUser(final Long id, final UserEntity userEntity);

    Optional<UserEntity> getUser(final Long id);

    Set<UserEntity> getUsers();
}
