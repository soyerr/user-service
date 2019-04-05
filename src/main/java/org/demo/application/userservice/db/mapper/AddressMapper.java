package org.demo.application.userservice.db.mapper;

import org.demo.application.userservice.db.model.UserAddressEntity;
import org.demo.application.userservice.model.Address;
import org.springframework.stereotype.Component;

@Component
class AddressMapper {

    Address convertFrom(UserAddressEntity userAddressEntity){
        return new Address(userAddressEntity.getStreetName(),userAddressEntity.getHouseNumber(),userAddressEntity.getFlatNumber());
    }

    UserAddressEntity convertTo(Address address){
        return new UserAddressEntity(address.getStreetName(),address.getHouseNumber(),address.getFlatNumber());
    }
}
