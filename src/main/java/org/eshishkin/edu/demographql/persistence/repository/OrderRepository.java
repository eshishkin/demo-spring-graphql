package org.eshishkin.edu.demographql.persistence.repository;

import org.eshishkin.edu.demographql.persistence.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findByCustomerId(String id);
}