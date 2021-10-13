package com.ecommerce.repository;

import org.springframework.data.repository.CrudRepository;

import com.ecommerce.model.OrderItem;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {

}
