package org.eshishkin.edu.demographql.model;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class OrderRequest {
    @Email
    private String email;

    @NotBlank
    @Size(max = 255)
    private String description;

    @NotEmpty
    private List<OrderItemRequest> items;

    @Data
    public static class OrderItemRequest {
        private String code;
        private Integer amount;
    }
}
