package org.demo.application.userservice.service;

import org.assertj.core.util.Sets;
import org.demo.application.userservice.controller.model.RestAddress;
import org.demo.application.userservice.controller.model.RestUser;
import org.demo.application.userservice.controller.model.RestUserDetails;
import org.demo.application.userservice.db.UserDbService;
import org.demo.application.userservice.exception.NotExistUserException;
import org.demo.application.userservice.model.Address;
import org.demo.application.userservice.model.User;
import org.demo.application.userservice.util.ModelBuilder;
import org.demo.application.userservice.util.RestModelBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.demo.application.userservice.util.RestModelBuilder.buildRestUser;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private AddressObjectMapper addressObjectMapper;

    @Mock
    private UserObjectMapper userObjectMapper;

    @Mock
    private UserDbService userDbService;

    @InjectMocks
    private UserService userService;

    @Test
    public void whenUserIsPassedToSave_ShouldBeConverted_ShouldBePassedToDBLayer(){

        //given
        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);
        RestUser restUser = buildRestUser();
        User user = ModelBuilder.buildUser(ModelBuilder.buildAddress());

        when(userObjectMapper.covertTo(eq(restUser))).thenReturn(user);
        when(userDbService.save(argumentCaptor.capture())).thenReturn(1L);

        //when

        Long userId = userService.save(restUser);

        //then

        User argumentCaptorValue = argumentCaptor.getValue();

        assertThat(userId).isEqualTo(1L);
        assertThat(argumentCaptorValue.getName()).isEqualTo(user.getName());
        assertThat(argumentCaptorValue.getSurname()).isEqualTo(user.getSurname());
        assertThat(argumentCaptorValue.getAge()).isEqualTo(user.getAge());
        assertThat(argumentCaptorValue.getAddresses()).containsAll(user.getAddresses());
    }

    @Test(expected = NotExistUserException.class)
    public void whenAddressIsPassedToSave_UserDoesNotExistInDB_ExceptionShouldBeThrown(){

        //given
        Long userId = 1L;
        RestAddress restAddress = RestModelBuilder.buildRestAddress();
        Address address = ModelBuilder.buildAddress();

        when(addressObjectMapper.covertTo(eq(restAddress))).thenReturn(address);
        when(userDbService.getUser(eq(userId))).thenThrow(new NotExistUserException("exception"));

        //when
        userService.updateAddress(userId,restAddress);

    }

    @Test
    public void whenAddressIsPassedToUpdate_UserExistsInDB_AddressShouldBeSaved_UserShouldBeSavedInDB(){

        //given
        Long userId = 1L;
        RestAddress restAddress = RestModelBuilder.buildRestAddress();
        Address address = new Address(restAddress.getStreetname(),restAddress.getHousenumber(),restAddress.getFlatnumber());
        User user = ModelBuilder.buildUser(ModelBuilder.buildAddress());

        ArgumentCaptor<User> argumentCaptor = ArgumentCaptor.forClass(User.class);


        when(addressObjectMapper.covertTo(eq(restAddress))).thenReturn(address);
        when(userDbService.getUser(eq(userId))).thenReturn(user);

        //when
        userService.updateAddress(userId,restAddress);
        verify(userDbService,times(1)).update(eq(userId),argumentCaptor.capture());

        //then
        User capturedUser = argumentCaptor.getValue();

        assertThat(capturedUser.getAddresses()).hasSize(2);

        assertThat(capturedUser.getAddresses()).extracting("streetName").containsExactlyInAnyOrder("street0","StreetName");
        assertThat(capturedUser.getAddresses()).extracting("houseNumber").containsExactlyInAnyOrder(0,10);
        assertThat(capturedUser.getAddresses()).extracting("flatNumber").containsExactlyInAnyOrder(0,5);
    }

    @Test
    public void whenGetUsersMethodIsCalled_NoUsersInDB_EmptyMapShouldBeReturned(){

        //given
        when(userDbService.getUsers()).thenReturn(Sets.newHashSet());

        //when
        Set<RestUserDetails> userDetails = userService.getUsers();

        //then
        assertThat(userDetails).isEmpty();
    }

    @Test
    public void whenGetUsersMethodIsCalled_UsersExistinDb_UsersShouldBeMapped(){

        //given
        Set<User> userSet = new HashSet<>();
        User firstUser = ModelBuilder.buildSpecificUser(0,ModelBuilder.buildSpecificAddress(0));
        User secondUser = ModelBuilder.buildSpecificUser(0,ModelBuilder.buildSpecificAddress(1));
        User thirdUser  = ModelBuilder.buildSpecificUser(0,ModelBuilder.buildSpecificAddress(2));
        userSet.add(firstUser);
        userSet.add(secondUser);
        userSet.add(thirdUser);

        RestUser convertedFirstUser = RestModelBuilder.buildSpecificRestUser(0);
        RestUser convertedSecondUser = RestModelBuilder.buildSpecificRestUser(1);
        RestUser convertedThirdUser = RestModelBuilder.buildSpecificRestUser(2);

        RestAddress convertedFirstAddress = RestModelBuilder.buildSpecificRestAddress(0);
        Set<RestAddress> firstAddressSet = new HashSet<>();
        firstAddressSet.add(convertedFirstAddress);

        RestAddress convertedSecondAddress = RestModelBuilder.buildSpecificRestAddress(1);
        Set<RestAddress> secondAddressSet = new HashSet<>();
        secondAddressSet.add(convertedSecondAddress);

        RestAddress convertedThirdAddress = RestModelBuilder.buildSpecificRestAddress(2);
        Set<RestAddress> thirdAddressSet = new HashSet<>();
        thirdAddressSet.add(convertedThirdAddress);

        when(userDbService.getUsers()).thenReturn(userSet);

        when(userObjectMapper.covertFrom(eq(firstUser))).thenReturn(convertedFirstUser);
        when(userObjectMapper.covertFrom(eq(secondUser))).thenReturn(convertedSecondUser);
        when(userObjectMapper.covertFrom(eq(thirdUser))).thenReturn(convertedThirdUser);

        when(addressObjectMapper.covertFrom(eq(firstUser.getAddresses()))).thenReturn(firstAddressSet);
        when(addressObjectMapper.covertFrom(eq(secondUser.getAddresses()))).thenReturn(secondAddressSet);
        when(addressObjectMapper.covertFrom(eq(thirdUser.getAddresses()))).thenReturn(thirdAddressSet);


        //when
        Set<RestUserDetails> userDetails = userService.getUsers();

        //then
        assertThat(userDetails.size()).isEqualTo(3);

        RestUserDetails firstUserDetails = userDetails.stream().filter(abc -> abc.getUser() == convertedFirstUser).findFirst().get();
        assertThat(firstUserDetails.getUser().getName()).isEqualTo(convertedFirstUser.getName());
        assertThat(firstUserDetails.getUser().getSurname()).isEqualTo(convertedFirstUser.getSurname());
        assertThat(firstUserDetails.getUser().getAge()).isEqualTo(convertedFirstUser.getAge());
        assertThat(firstUserDetails.getAddresses()).containsExactlyInAnyOrderElementsOf(firstAddressSet);

        RestUserDetails secondUserDetails = userDetails.stream().filter(abc -> abc.getUser() == convertedSecondUser).findFirst().get();
        assertThat(secondUserDetails.getUser().getName()).isEqualTo(convertedSecondUser.getName());
        assertThat(secondUserDetails.getUser().getSurname()).isEqualTo(convertedSecondUser.getSurname());
        assertThat(secondUserDetails.getUser().getAge()).isEqualTo(convertedSecondUser.getAge());
        assertThat(secondUserDetails.getAddresses()).containsExactlyInAnyOrderElementsOf(secondAddressSet);

        RestUserDetails thirdUserDetails = userDetails.stream().filter(abc -> abc.getUser() == convertedThirdUser).findFirst().get();
        assertThat(thirdUserDetails.getUser().getName()).isEqualTo(convertedThirdUser.getName());
        assertThat(thirdUserDetails.getUser().getSurname()).isEqualTo(convertedThirdUser.getSurname());
        assertThat(thirdUserDetails.getUser().getAge()).isEqualTo(convertedThirdUser.getAge());
        assertThat(thirdUserDetails.getAddresses()).containsExactlyInAnyOrderElementsOf(thirdAddressSet);
    }
}