package org.eshishkin.edu.demographql.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eshishkin.edu.demographql.persistence.model.Order;
import org.eshishkin.edu.demographql.persistence.repository.OrderRepository;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderRepository orderRepository;

    @QueryMapping
    public List<Order> simple(@Argument Integer id) {
        log.info("Query Request [id] - {}", id);
        return new ArrayList<>();
    }

    @MutationMapping
    public List<Order> create(@Argument String description) {
        log.info("Mutation Request [description] - {}", description);
        return new ArrayList<>();
    }
}
