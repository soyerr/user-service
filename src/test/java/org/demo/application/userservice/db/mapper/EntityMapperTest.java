package org.demo.application.userservice.db.mapper;

import org.demo.application.userservice.db.model.UserAddressEntity;
import org.demo.application.userservice.db.model.UserEntity;
import org.demo.application.userservice.model.Address;
import org.demo.application.userservice.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.demo.application.userservice.util.DataBaseEntityBuilder.buildSingleAddressEntity;
import static org.demo.application.userservice.util.DataBaseEntityBuilder.buildSingleUserEntity;
import static org.demo.application.userservice.util.ModelBuilder.buildAddress;
import static org.demo.application.userservice.util.ModelBuilder.buildUser;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EntityMapperTest {

    @InjectMocks
    private EntityMapper entityMapper;

    @Mock
    private AddressMapper addressMapper;

    @Test
    public void shouldConvertFromDBEntityToCommonModel(){

        //given
        Address address = buildAddress();
        UserAddressEntity userAddressEntity = buildSingleAddressEntity();
        UserEntity userEntity = buildSingleUserEntity(userAddressEntity);


        when(addressMapper.convertFrom(eq(userAddressEntity))).thenReturn(address);

        //when
        User convertedFrom = entityMapper.convertFrom(userEntity);

        //then

        assertThat(convertedFrom).hasFieldOrPropertyWithValue("name","name0");
        assertThat(convertedFrom).hasFieldOrPropertyWithValue("surname","surname0");
        assertThat(convertedFrom).hasFieldOrPropertyWithValue("age",0);

        assertThat(convertedFrom.getAddresses()).contains(address);
    }

    @Test
    public void shouldConvertFromCommonModelToDBEntity(){

        //given
        Address address = buildAddress();
        User user = buildUser(address);
        UserAddressEntity userAddressEntity = buildSingleAddressEntity();

        when(addressMapper.convertTo(eq(address))).thenReturn(userAddressEntity);

        //when
        UserEntity convertedTo = entityMapper.convertTo(user);

        //then
        assertThat(convertedTo).hasFieldOrPropertyWithValue("name","name0");
        assertThat(convertedTo).hasFieldOrPropertyWithValue("surname","surname0");
        assertThat(convertedTo).hasFieldOrPropertyWithValue("age",0);

        assertThat(convertedTo.getAddresses()).contains(userAddressEntity);

    }
}