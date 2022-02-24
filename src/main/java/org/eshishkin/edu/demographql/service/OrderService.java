package org.eshishkin.edu.demographql.service;

import lombok.RequiredArgsConstructor;
import org.eshishkin.edu.demographql.exception.UserNotFoundException;
import org.eshishkin.edu.demographql.model.OrderRequest;
import org.eshishkin.edu.demographql.persistence.model.OrderEntity;
import org.eshishkin.edu.demographql.persistence.model.OrderItemEntity;
import org.eshishkin.edu.demographql.persistence.repository.OrderRepository;
import org.eshishkin.edu.demographql.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    public List<OrderEntity> getOrders(Long id) {
        return id != null ? orderRepository.findAllById(id) : orderRepository.findAll();
    }

    @Transactional
    public OrderEntity create(OrderRequest request) {
        var user = userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found: " + request.getEmail()));

        OrderEntity order = new OrderEntity();
        order.setDescription(request.getDescription());
        order.setState(OrderEntity.State.IN_PROGRESS);
        order.setCustomer(user);
        order.setCreated(LocalDate.now());
        order.setItems(request.getItems().stream()
                .map(i -> {
                    var item = new OrderItemEntity();
                    item.setCode(i.getCode());
                    item.setName("Name: " + i.getCode());
                    item.setAmount(i.getAmount());
                    item.setMerchantId(0L);
                    item.setOrder(order);
                    return item;
                })
                .collect(Collectors.toList())
        );

        return orderRepository.save(order);
    }
}
