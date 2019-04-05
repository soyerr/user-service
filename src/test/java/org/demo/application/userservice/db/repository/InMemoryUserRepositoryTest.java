package org.demo.application.userservice.db.repository;

import com.google.common.collect.Sets;

import org.demo.application.userservice.db.model.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.demo.application.userservice.util.DataBaseEntityBuilder.buildSingleAddressEntity;
import static org.demo.application.userservice.util.DataBaseEntityBuilder.buildSingleUserEntity;
import static org.demo.application.userservice.util.DataBaseEntityBuilder.buildUserEntities;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InMemoryUserRepositoryTest {

    @Mock
    private Map<Long,UserEntity> storage;

    @InjectMocks
    private InMemoryUserRepository repository;

    @Test
    public void whenUserIsPassed_UserShouldBeSaveInDB(){

        //given
        UserEntity userEntity = buildSingleUserEntity(buildSingleAddressEntity());

        //when

        Long id = repository.addUser(userEntity);

        //then

        verify(storage,times(1)).put(eq(id),eq(userEntity));
    }

    @Test
    public void whenSameUserIsPassedTwice_UserShouldBeSaveInDBInDifferentId(){

        //given
        UserEntity firstUser = buildSingleUserEntity(buildSingleAddressEntity());
        UserEntity secondUser = buildSingleUserEntity(buildSingleAddressEntity());

        //when

        Long firstId = repository.addUser(firstUser);
        when(storage.size()).thenReturn(1);
        Long secondId = repository.addUser(secondUser);

        //then

        assertThat(firstId).isNotEqualTo(secondId);

        verify(storage,times(1)).put(eq(firstId),eq(firstUser));
        verify(storage,times(1)).put(eq(secondId),eq(secondUser));

    }

    @Test
    public void whenUserExistsInDB_UserShouldBeReturnedBasedOnId(){

        //given
        Long id = 1L;
        UserEntity userEntity = buildSingleUserEntity(buildSingleAddressEntity());
        when(storage.get(eq(id))).thenReturn(userEntity);

        //when

        Optional<UserEntity> existingUser = repository.getUser(id);

        //when

        assertThat(existingUser.get()).isEqualTo(userEntity);
    }

    @Test
    public void whenUserDoesNotExistsInDB_UserShouldNotBeReturnedBasedOnId(){

        //given
        Long id = 1L;
        when(storage.get(eq(id))).thenReturn(null);

        //when

        Optional<UserEntity> existingUser = repository.getUser(id);

        //when

        assertThat(existingUser).isNotPresent();
    }

    @Test
    public void whenDBDoesNotContainsUsers_UsersShouldNotBeReturned(){

        //given

        Set<UserEntity> userEntities = buildUserEntities(10);
        when(storage.values()).thenReturn(userEntities);

        //when

        Set<UserEntity> users = repository.getUsers();

        //then

        assertThat(users).containsAll(userEntities);

    }

    @Test
    public void whenDBContainsUsers_UsersShouldBeReturned(){

        //given

        when(storage.values()).thenReturn(Sets.newHashSet());

        //when

        Set<UserEntity> users = repository.getUsers();

        //then
        assertThat(users).isEmpty();
    }


}