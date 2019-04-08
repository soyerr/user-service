package org.demo.application.userservice.service;

import org.demo.application.userservice.controller.model.RestUser;
import org.demo.application.userservice.model.User;
import org.demo.application.userservice.util.RestModelBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class UserObjectMapperTest {

    private UserObjectMapper userObjectMapper;

    @Before
    public void init(){
        this.userObjectMapper = new UserObjectMapper();
    }

    @Test
    public void shouldConvertRestObjectToCommonUserObject(){

        //given
        RestUser restUser = RestModelBuilder.buildRestUser();

        //when
        User user = userObjectMapper.covertTo(restUser);

        //then
        assertThat(user.getAddresses()).isEmpty();
        assertThat(user.getAge()).isEqualTo(0);
        assertThat(user.getSurname()).isEqualTo("Smith");
        assertThat(user.getName()).isEqualTo("John");
    }
}