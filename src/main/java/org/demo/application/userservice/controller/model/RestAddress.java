package org.demo.application.userservice.controller.model;

import java.io.Serializable;

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
public class RestAddress implements Serializable {

    @NotBlank(message = "Street name cannot be empty/blank")
    private String streetname;

    @Min(value = 1,message = "House number has to be equal or higher than 1")
    private int housenumber;

    @Min(value = 1,message = "Flat number has to be equal or higher than 1")
    private int flatnumber;
}
