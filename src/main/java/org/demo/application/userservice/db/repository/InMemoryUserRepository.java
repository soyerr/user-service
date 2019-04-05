package org.demo.application.userservice.db.repository;

import com.google.common.collect.Sets;

import org.demo.application.userservice.db.model.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Repository
public class InMemoryUserRepository implements UserRepository {

    private Map<Long,UserEntity> memoryStorage;

    public InMemoryUserRepository(Map<Long,UserEntity> memoryStorage){
        this.memoryStorage = memoryStorage;
    }

    @Override
    public Long addUser(final UserEntity userEntity) {
        Long id = generateId();
        memoryStorage.put(id,userEntity);
        return id;
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
