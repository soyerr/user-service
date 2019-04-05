package org.demo.application.userservice.db.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEntity {

    private String name;

    private String surname;

    private int age;

    private Set<UserAddressEntity> addresses;
}
