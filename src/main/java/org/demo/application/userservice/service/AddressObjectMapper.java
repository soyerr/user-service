package org.demo.application.userservice.service;

import org.demo.application.userservice.controller.model.RestAddress;
import org.demo.application.userservice.model.Address;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
class AddressObjectMapper {

    Address covertTo(final RestAddress address) {
        return new Address(address.getStreetname(),address.getHousenumber(),address.getFlatnumber());
    }

    Set<RestAddress> covertFrom(final Set<Address> address) {
        return address.stream()
                .map(valueToConvertFrom -> new RestAddress(valueToConvertFrom.getStreetName(),
                        valueToConvertFrom.getHouseNumber(),valueToConvertFrom.getFlatNumber()))
                .collect(Collectors.toSet());
    }

}
