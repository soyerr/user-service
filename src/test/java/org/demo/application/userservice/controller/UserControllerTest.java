package org.demo.application.userservice.controller;

import com.google.common.collect.Sets;
import com.google.gson.Gson;

import org.demo.application.userservice.UserServiceApplication;
import org.demo.application.userservice.controller.model.RestAddress;
import org.demo.application.userservice.controller.model.RestUser;
import org.demo.application.userservice.controller.model.RestUserDetails;
import org.demo.application.userservice.exception.NotExistUserException;
import org.demo.application.userservice.service.UserService;
import org.demo.application.userservice.util.RestModelBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @MockBean
    private UserService userService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenCreateUserEndpointIsCalled_201StatusShouldBeReturned_IdShouldBeReturned() throws Exception {

        //given
        String json = buildNewUserAsJson();

        //when

        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();
        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"id\":0");
    }

    @Test
    public void whenAddAddressToUserEndpointIsCalled_201StatusShouldBeReturned() throws Exception {

        //given
        String json = buildAddressAsJson();
        Long userId = 1L;

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();
        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
    }

    @Test
    public void whenAddAddressToUserEndpointIsCalled_UserDoesNotExist_404StatusShouldBeReturned() throws Exception {

        //given
        String json = buildAddressAsJson();
        Long userId = 1L;

        doThrow(new NotExistUserException("exception")).when(userService).updateAddress(anyLong(),any(RestAddress.class));

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();
        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"exception\"]");

    }

    @Test
    public void whenGetUsersEndpointIsCalled_UsersDoNotExist_200StatusShouldBeReturned_EmptyListShouldBeReturned() throws Exception {

        //given

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andReturn()
                .getResponse();
        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("[]");

    }

    @Test
    public void whenGetUsersEndpointIsCalled_UsersWithoutAddressesExist_200StatusShouldBeReturned_ListWithUserAndWithoutAddressesShouldBeReturned() throws Exception {

        //given
        Set<RestUserDetails> userDetails = new HashSet<>();
        userDetails.add(RestUserDetails.builder()
                .user(RestModelBuilder.buildSpecificRestUser(1))
                .addresses(Sets.newHashSet())
                .build());

        when(userService.getUsers()).thenReturn(userDetails);

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.get("/user"))
                .andReturn()
                .getResponse();
        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"user\":{\"name\":\"name1\",\"surname\":\"surname1\",\"age\":1}");
        assertThat(mvcResult.getContentAsString()).contains("\"addresses\":[]");
    }

    @Test
    public void whenGetUsersEndpointIsCalled_UsersWithAddressesExist_200StatusShouldBeReturned_ListWithUserAndWithAddressesShouldBeReturned() throws Exception {

    }

    private String buildAddressAsJson() {
        RestAddress restAddress= new RestAddress();
        restAddress.setFlatnumber(5);
        restAddress.setHousenumber(10);
        restAddress.setStreetname("WelcomeStreet");

        return new Gson().toJson(restAddress);
    }

    private String buildNewUserAsJson() {
        RestUser restUser = new RestUser();
        restUser.setAge(5);
        restUser.setName("John");
        restUser.setSurname("Smith");

        return new Gson().toJson(restUser);
    }
}