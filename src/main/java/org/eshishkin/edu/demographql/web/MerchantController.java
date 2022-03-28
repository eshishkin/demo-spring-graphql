package org.eshishkin.edu.demographql.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.eshishkin.edu.demographql.model.CreateMerchantRequest;
import org.eshishkin.edu.demographql.persistence.model.MerchantEntity;
import org.eshishkin.edu.demographql.service.MerchantService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MerchantController {
    private final MerchantService merchantService;

    @Transactional
    @MutationMapping(name = "merchant")
    public Mono<MerchantEntity> create(@Valid @Argument CreateMerchantRequest request) {
        return merchantService.create(request);
    }
}
