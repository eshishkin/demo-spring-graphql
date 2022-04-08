package org.eshishkin.edu.demographql.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dataloader.DataLoader;
import org.eshishkin.edu.demographql.model.OrderRequest;
import org.eshishkin.edu.demographql.persistence.model.MerchantEntity;
import org.eshishkin.edu.demographql.persistence.model.OrderEntity;
import org.eshishkin.edu.demographql.persistence.model.OrderItemEntity;
import org.eshishkin.edu.demographql.service.MerchantService;
import org.eshishkin.edu.demographql.service.OrderService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.graphql.execution.BatchLoaderRegistry;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final MerchantService merchantService;
    private final BatchLoaderRegistry registry;

    @PostConstruct
    public void init() {
        registry.forTypePair(Long.class, MerchantEntity.class)
                .registerBatchLoader((ids, env) -> merchantService.find(ids));
    }

    @QueryMapping
    public Flux<OrderEntity> simple(@Argument Long id) {
        log.info("Query Request [id] - {}", id);
        return orderService.getOrders(id);
    }

    @SchemaMapping(typeName = "Item", field = "merchant")
    public CompletableFuture<MerchantEntity> merchant(OrderItemEntity item, DataLoader<Long, MerchantEntity> loader) {
        return loader.load(item.getMerchantId());
    }

    @MutationMapping("order")
    public OrderEntity createOrder(@Valid @Argument OrderRequest request) {
        return orderService.create(request);
    }
}
