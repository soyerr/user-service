package org.demo.application.userservice.db.mapper;

import org.demo.application.userservice.db.model.UserAddressEntity;
import org.demo.application.userservice.model.Address;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.demo.application.userservice.util.DataBaseEntityBuilder.buildSingleAddressEntity;
import static org.demo.application.userservice.util.ModelBuilder.buildAddress;

public class AddressMapperTest {

    private AddressMapper addressMapper;

    @Before
    public void init(){
        this.addressMapper = new AddressMapper();
    }

    @Test
    public void shouldConvertFromDBEntityToCommonModel(){

        //given
        UserAddressEntity addressEntity = buildSingleAddressEntity();

        //when
        Address convertedFrom = addressMapper.convertFrom(addressEntity);

        //then
        assertThat(convertedFrom).hasFieldOrPropertyWithValue("streetName","street0");
        assertThat(convertedFrom).hasFieldOrPropertyWithValue("houseNumber",0);
        assertThat(convertedFrom).hasFieldOrPropertyWithValue("flatNumber",0);
    }

    @Test
    public void shouldConvertFromCommonModelToDBEntity(){

        //given
        Address address = buildAddress();

        //when
        UserAddressEntity convertedTo = addressMapper.convertTo(address);

        //then
        assertThat(convertedTo).hasFieldOrPropertyWithValue("streetName","street0");
        assertThat(convertedTo).hasFieldOrPropertyWithValue("houseNumber",0);
        assertThat(convertedTo).hasFieldOrPropertyWithValue("flatNumber",0);
    }
}