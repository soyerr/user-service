package org.demo.application.userservice.db.mapper;

import org.demo.application.userservice.db.model.UserAddressEntity;
import org.demo.application.userservice.db.model.UserEntity;
import org.demo.application.userservice.model.Address;
import org.demo.application.userservice.model.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EntityMapper {

    private final AddressMapper addressMapper;

    public EntityMapper(AddressMapper addressMapper){

        this.addressMapper = addressMapper;
    }

    public User convertFrom(UserEntity userEntity){
        Set<Address> addresses = userEntity.getAddresses().stream()
                .map(address -> addressMapper.convertFrom(address))
                .collect(Collectors.toSet());
        return new User(userEntity.getName(),userEntity.getSurname(),userEntity.getAge(),addresses);
    }

    public UserEntity convertTo(User user){
        Set<UserAddressEntity> entities = user.getAddresses().stream()
                .map(address -> addressMapper.convertTo(address))
                .collect(Collectors.toSet());

        return new UserEntity(user.getName(),user.getSurname(),user.getAge(), entities);
    }
}
