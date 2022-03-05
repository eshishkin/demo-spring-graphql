package org.eshishkin.edu.demographql.web;

import graphql.schema.DataFetchingEnvironment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eshishkin.edu.demographql.model.OrderRequest;
import org.eshishkin.edu.demographql.persistence.model.MerchantEntity;
import org.eshishkin.edu.demographql.persistence.model.OrderEntity;
import org.eshishkin.edu.demographql.service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @QueryMapping
    public List<OrderEntity> simple(@Argument Long id) {
        log.info("Query Request [id] - {}", id);
        return orderService.getOrders(id);
    }

    @SchemaMapping(typeName = "Item", field = "merchant")
    public MerchantEntity merchant(DataFetchingEnvironment env) {
        var entity = new MerchantEntity();
        entity.setName("Default Merchant");
        return entity;
    }

    @MutationMapping("order")
    public OrderEntity createOrder(@Argument OrderRequest request) {
        return orderService.create(request);
    }
}
