package org.demo.application.userservice.controller;

import com.google.gson.Gson;

import org.demo.application.userservice.UserServiceApplication;
import org.demo.application.userservice.util.RestModelBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceApplication.class)
public class UserControllerValidationTest {

    private MockMvc mockMvc;

    @Autowired
    WebApplicationContext webApplicationContext;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void whenCreateUserEndpointIsCalled_EmptyNameValue_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithEmptyName());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"User name cannot be empty/blank!\"]");
    }

    @Test
    public void whenCreateUserEndpointIsCalled_BlankNameValue_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithBlankName());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"User name cannot be empty/blank!\"]");
    }

    @Test
    public void whenCreateUserEndpointIsCalled_MissingNameKey_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithoutName());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"User name cannot be empty/blank!\"]");
    }

    @Test
    public void whenCreateUserEndpointIsCalled_EmptySurnameValue_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithEmptySurname());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"User surname cannot be empty/blank!\"]");
    }

    @Test
    public void whenCreateUserEndpointIsCalled_BlankSurnameValue_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithBlankSurname());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"User surname cannot be empty/blank!\"]");
    }

    @Test
    public void whenCreateUserEndpointIsCalled_MissingSurnameKey_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithoutSurname());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"User surname cannot be empty/blank!\"]");
    }

    @Test
    public void whenCreateUserEndpointIsCalled_AgeValueLowerThanBottomBoundary_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithAgeBelowLowBoundary());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"Minimum user's age has to be equal or higher than 1\"]");
    }

    @Test
    public void whenCreateUserEndpointIsCalled_AgeValueExceedUpperBoundary_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithAgeAboveTopBoundary());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"Maximum user's age cannot exceed 150\"]");
    }

    @Test
    public void whenCreateUserEndpointIsCalled_MissingAgeKey_406ShouldBeReturned() throws Exception {

        //given
        String json = new Gson().toJson(RestModelBuilder.buildRestUserWithoutAge());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"Minimum user's age has to be equal or higher than 1\"]");
    }

    @Test
    public void whenCreateAddressEndpointIsCalled_MissingStreetKey_406ShouldBeReturned() throws Exception {

        //given
        Long userId = 1L;
        String json = new Gson().toJson(RestModelBuilder.buildRestAddressWithoutStreet());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"Street name cannot be empty/blank\"]");

    }

    @Test
    public void whenCreateAddressEndpointIsCalled_EmptyStreetValue_406ShouldBeReturned() throws Exception {

        //given
        Long userId = 1L;
        String json = new Gson().toJson(RestModelBuilder.buildRestAddressWithEmptyStreeet());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"Street name cannot be empty/blank\"]");
    }

    @Test
    public void whenCreateAddressEndpointIsCalled_BlankStreetValue_406ShouldBeReturned() throws Exception {

        //given
        Long userId = 1L;
        String json = new Gson().toJson(RestModelBuilder.buildRestAddressWithBlankStreeet());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"Street name cannot be empty/blank\"]");
    }

    @Test
    public void whenCreateAddressEndpointIsCalled_MissingFlatNumberKey_406ShouldBeReturned() throws Exception {

        //given
        Long userId = 1L;
        String json = new Gson().toJson(RestModelBuilder.buildRestAddressWithoutFlat());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"Flat number has to be equal or higher than 1\"]");

    }

    @Test
    public void whenCreateAddressEndpointIsCalled_FlatValueLowerThanBottomBoundary_406ShouldBeReturned() throws Exception {

        //given
        Long userId = 1L;
        String json = new Gson().toJson(RestModelBuilder.buildRestAddressWithAFlatBelowLowBoundary());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"Flat number has to be equal or higher than 1\"]");
    }

    @Test
    public void whenCreateAddressEndpointIsCalled_MissingHouseNumberKey_406ShouldBeReturned() throws Exception {

        //given
        Long userId = 1L;
        String json = new Gson().toJson(RestModelBuilder.buildRestAddressWithoutHouse());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"House number has to be equal or higher than 1\"]");

    }

    @Test
    public void whenCreateAddressEndpointIsCalled_HouseNumberValueLowerThanBottomBoundary_406ShouldBeReturned() throws Exception {

        //given
        Long userId = 1L;
        String json = new Gson().toJson(RestModelBuilder.buildRestAddressWithAHouseBelowLowBoundary());

        //when
        MockHttpServletResponse mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/user/{id}/address",userId)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(json))
                .andReturn()
                .getResponse();

        //then

        assertThat(mvcResult.getStatus()).isEqualTo(HttpStatus.NOT_ACCEPTABLE.value());
        assertThat(mvcResult.getContentType()).isEqualTo(MediaType.APPLICATION_JSON_UTF8.toString());
        assertThat(mvcResult.getContentAsString()).contains("\"exceptionMessages\":[\"House number has to be equal or higher than 1\"]");
    }
}
