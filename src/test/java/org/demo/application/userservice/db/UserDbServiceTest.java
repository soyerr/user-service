package org.demo.application.userservice.db;

import org.demo.application.userservice.db.mapper.EntityMapper;
import org.demo.application.userservice.db.model.UserEntity;
import org.demo.application.userservice.db.repository.UserRepository;
import org.demo.application.userservice.exception.NotExistUserException;
import org.demo.application.userservice.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.demo.application.userservice.util.DataBaseEntityBuilder.buildSingleAddressEntity;
import static org.demo.application.userservice.util.DataBaseEntityBuilder.buildSingleUserEntity;
import static org.demo.application.userservice.util.ModelBuilder.buildAddress;
import static org.demo.application.userservice.util.ModelBuilder.buildUser;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserDbServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private EntityMapper entityMapper;

    @InjectMocks
    private DatabaseService userDbService;

    @Test
    public void whenUserIsPresentInDB_UserShouldBeReturned(){

        //given
        Long id = 1L;
        UserEntity userEntity = buildSingleUserEntity(buildSingleAddressEntity());
        User user = buildUser(buildAddress());
        when(userRepository.getUser(eq(id))).thenReturn(Optional.of(userEntity));

        when(entityMapper.convertFrom(eq(userEntity))).thenReturn(user);

        //when
        User result = userDbService.getUser(id);

        //then
        assertThat(result.getAddresses()).containsExactlyInAnyOrderElementsOf(user.getAddresses());
        assertThat(result.getAge()).isEqualTo(user.getAge());
        assertThat(result.getName()).isEqualTo(user.getName());
        assertThat(result.getSurname()).isEqualTo(user.getSurname());
    }

    @Test(expected = NotExistUserException.class)
    public void whenUserIsMissing_ExceptionShouldBeThrown(){

        //given
        Long id = 1L;
        when(userRepository.getUser(eq(id))).thenReturn(Optional.empty());

        //when
        userDbService.getUser(id);

        //then

    }
}