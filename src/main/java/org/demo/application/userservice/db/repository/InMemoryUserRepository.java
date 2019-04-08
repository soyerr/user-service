package org.demo.application.userservice.db.repository;

import com.google.common.collect.Sets;

import org.demo.application.userservice.db.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@AllArgsConstructor
public class InMemoryUserRepository implements UserRepository {

    private Map<Long,UserEntity> memoryStorage;

    @Override
    public Long addUser(final UserEntity userEntity) {
        log.debug("Adding following entity {} to DB",userEntity);

        Long id = generateId();
        log.debug("Generated id {} for entity {} ",id,userEntity);

        memoryStorage.put(id,userEntity);
        log.debug("Entity with id {} saved in DB",id);

        return id;
    }

    @Override
    public void updateUser(final Long id, final UserEntity userEntity) {
        memoryStorage.merge(id,userEntity,(oldVal,newVal) -> newVal);
        log.debug("User {} with id {} updated in DB",userEntity,id);
    }

    @Override
    public Optional<UserEntity> getUser(final Long id) {
        return Optional.ofNullable(memoryStorage.get(id));
    }

    @Override
    public Set<UserEntity> getUsers() {
        return Sets.newHashSet(memoryStorage.values());
    }

    private Long generateId(){
        return (long) memoryStorage.size();
    }
}
