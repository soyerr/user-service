package org.demo.application.userservice.controller.model;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RestUser implements Serializable {

    @NotBlank(message = "User name cannot be empty/blank!")
    private String name;

    @NotBlank(message = "User surname cannot be empty/blank!")
    private String surname;

    @Min(value = 1,message = "Minimum user's age has to be equal or higher than 1")
    @Max(value = 150,message = "Maximum user's age cannot exceed 150")
    private int age;

}
