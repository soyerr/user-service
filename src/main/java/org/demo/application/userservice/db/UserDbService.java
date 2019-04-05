package org.demo.application.userservice.db;

import org.demo.application.userservice.db.mapper.EntityMapper;
import org.demo.application.userservice.db.model.UserEntity;
import org.demo.application.userservice.db.repository.UserRepository;
import org.demo.application.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserDbService {

    private UserRepository userRepository;
    private EntityMapper entityMapper;

    public UserDbService(UserRepository userRepository, EntityMapper entityMapper){

        this.userRepository = userRepository;
        this.entityMapper = entityMapper;
    }

    public Long addUser(User user){
        UserEntity userEntity = entityMapper.convertTo(user);
        return userRepository.addUser(userEntity);
    }

    public User getUser(Long id){
        return userRepository.getUser(id).map(entityMapper::convertFrom).orElseThrow(() -> new RuntimeException("basd"));
    }

    public Set<User> getUsers(){
        return userRepository.getUsers().stream()
                .map(entityMapper::convertFrom)
                .collect(Collectors.toSet());
    }
}
