package org.eshishkin.edu.demographql.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateMerchantRequest {

    @NotBlank
    private String name;
    @NotBlank
    private String locatedIn;
}
