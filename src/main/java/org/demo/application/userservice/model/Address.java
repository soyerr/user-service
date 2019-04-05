package org.demo.application.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Address {

    private String streetName;

    private int houseNumber;

    private int flatNumber;

}
