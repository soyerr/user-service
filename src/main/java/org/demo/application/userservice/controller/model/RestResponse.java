package org.demo.application.userservice.controller.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Set;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResponse implements Serializable {

    private Long id;

    private Set<String> exceptionMessages;

}
