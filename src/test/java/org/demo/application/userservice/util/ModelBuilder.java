package org.demo.application.userservice.util;

import com.google.common.collect.Sets;
import org.demo.application.userservice.model.Address;
import org.demo.application.userservice.model.User;

public class ModelBuilder {

    public static Address buildAddress() {
        return buildSpecificAddress(0);
    }

    public static User buildUser(Address address){
        return new User("name0", "surname0", 0, Sets.newHashSet(address));
    }

    public static User buildSpecificUser(int number, Address address){
        return new User("name"+number, "surname"+number, number, Sets.newHashSet(address));
    }

    public static Address buildSpecificAddress(int number){
        return new Address("street"+number,number,number);
    }
}
