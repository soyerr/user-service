package org.demo.application.userservice.model;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class User {

    private String name;

    private String surname;

    private int age;

    private Set<Address> addresses;
}
