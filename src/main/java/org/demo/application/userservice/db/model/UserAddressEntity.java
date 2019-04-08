package org.demo.application.userservice.db.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserAddressEntity {

    private String streetName;

    private int houseNumber;

    private int flatNumber;
}
