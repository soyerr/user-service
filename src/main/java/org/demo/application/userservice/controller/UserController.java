package org.demo.application.userservice.controller;

import org.demo.application.userservice.controller.model.RestAddress;
import org.demo.application.userservice.controller.model.RestResponse;
import org.demo.application.userservice.controller.model.RestUser;
import org.demo.application.userservice.controller.model.RestUserDetails;
import org.demo.application.userservice.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

import javax.validation.Valid;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @ApiOperation(value = "Get all users with their addresses")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found - invalid url"),
            @ApiResponse(code = 405, message = "Method not allowed"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Set<RestUserDetails>> getUsers() {
        Set<RestUserDetails> userDetails = userService.getUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDetails);
    }

    @ApiOperation(value = "Create new user")
    @ApiResponses({
            @ApiResponse(code = 201, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not found - invalid url"),
            @ApiResponse(code = 405, message = "Method not allowed"),
            @ApiResponse(code = 406, message = "Not Acceptable - provided model is not valid"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestResponse> createUser(@ApiParam("Data required for create new user") @Valid @RequestBody RestUser user) {
        Long userId = userService.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestResponse.builder()
                        .id(userId)
                        .build());
    }

    @ApiOperation(value = "Add address for existing user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 404, message = "Not Found - user behind provided id does not exist"),
            @ApiResponse(code = 405, message = "Method not allowed"),
            @ApiResponse(code = 406, message = "Not Acceptable - provided model is not valid"),
            @ApiResponse(code = 500, message = "Internal Server Error")
    })
    @PostMapping(path = "/{id}/address", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestResponse> createAddress(@ApiParam("Unique identifier of previously created user") @PathVariable("id") Long id,
                                                      @ApiParam("Data required for create new address")@Valid @RequestBody RestAddress address) {

        userService.updateAddress(id, address);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestResponse.builder()
                        .build());
    }
}
