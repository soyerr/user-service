package org.demo.application.userservice.controller.model;

import java.io.Serializable;
import java.util.Set;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@Builder
@EqualsAndHashCode
public class RestUserDetails implements Serializable {

    private RestUser user;

    private Set<RestAddress> addresses;
}
