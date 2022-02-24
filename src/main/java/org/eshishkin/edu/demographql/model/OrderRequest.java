package org.eshishkin.edu.demographql.model;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private String email;
    private String description;
    private List<OrderItemRequest> items;

    @Data
    public static class OrderItemRequest {
        private String code;
        private Integer amount;
    }
}
