package org.demo.application.userservice.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class Address {

    private String streetName;

    private int houseNumber;

    private int flatNumber;

}
