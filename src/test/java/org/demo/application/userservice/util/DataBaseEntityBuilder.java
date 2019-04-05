package org.demo.application.userservice.util;

import com.google.common.collect.Sets;

import org.demo.application.userservice.db.model.UserAddressEntity;
import org.demo.application.userservice.db.model.UserEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DataBaseEntityBuilder {

    public static Set<UserEntity> buildUserEntities(int numberOfEntities){
        return IntStream.rangeClosed(1,numberOfEntities)
                .mapToObj(objectNumber -> buildUserEntity(objectNumber,buildAddressEntity(objectNumber)))
                .collect(Collectors.toSet());
    }

    public static UserEntity buildSingleUserEntity(UserAddressEntity userAddressEntity){
        return new UserEntity("name0","surname0",0, Sets.newHashSet(userAddressEntity));
    }

    public static UserAddressEntity buildSingleAddressEntity(){
        return new UserAddressEntity("street0",0,0);
    }

    private static HashSet<UserAddressEntity> buildAddressEntity(final int objectNumber) {
        return Sets.newHashSet(new UserAddressEntity("street" + objectNumber, objectNumber, objectNumber));
    }

    private static UserEntity buildUserEntity(final int objectNumber, final Set<UserAddressEntity> userAddressEntity) {
        return new UserEntity("name"+objectNumber,"surname"+objectNumber,objectNumber, Sets.newHashSet(userAddressEntity));
    }


}
