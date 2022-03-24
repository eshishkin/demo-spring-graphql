package org.eshishkin.edu.demographql.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class CustomerRequest {

    @NotBlank
    @Size(min = 3)
    private String name;

    @Email
    private String email;
}
