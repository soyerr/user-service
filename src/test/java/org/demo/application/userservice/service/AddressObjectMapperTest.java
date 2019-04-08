package org.demo.application.userservice.service;

import org.demo.application.userservice.controller.model.RestAddress;
import org.demo.application.userservice.model.Address;
import org.demo.application.userservice.util.RestModelBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class AddressObjectMapperTest {

    private AddressObjectMapper addressObjectMapper;

    @Before
    public void init(){
        this.addressObjectMapper = new AddressObjectMapper();
    }

    @Test
    public void shouldConvertRestObjectToCommonAddressObject(){

        //given
        RestAddress restAddress = RestModelBuilder.buildRestAddress();

        //when
        Address address = addressObjectMapper.covertTo(restAddress);

        //then
        assertThat(address.getFlatNumber()).isEqualTo(5);
        assertThat(address.getHouseNumber()).isEqualTo(10);
        assertThat(address.getStreetName()).isEqualTo("StreetName");
    }
}