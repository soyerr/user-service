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

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<Set<RestUserDetails>> getUsers() {
        Set<RestUserDetails> userDetails = userService.getUsers();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userDetails);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestResponse> createUser(@Valid @RequestBody RestUser user) {
        Long userId = userService.save(user);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestResponse.builder()
                        .id(userId)
                        .build());
    }

    @PostMapping(path = "/{id}/address", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<RestResponse> createAddress(@PathVariable("id") Long id, @Valid @RequestBody RestAddress address) {

        userService.updateAddress(id, address);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RestResponse.builder()
                        .build());
    }
}
