package org.eshishkin.edu.demographql.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eshishkin.edu.demographql.model.CustomerRequest;
import org.eshishkin.edu.demographql.persistence.model.UserEntity;
import org.eshishkin.edu.demographql.service.CustomerService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @Transactional
    @MutationMapping(name = "customer")
    public Mono<UserEntity> createUser(@Valid @Argument CustomerRequest request) {
        return customerService.create(request);
    }
}
