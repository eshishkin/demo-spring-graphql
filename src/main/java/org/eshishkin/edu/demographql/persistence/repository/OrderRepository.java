package org.eshishkin.edu.demographql.persistence.repository;

import org.eshishkin.edu.demographql.persistence.model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findAllById(Long id);

    List<OrderEntity> findByCustomerId(String id);
}