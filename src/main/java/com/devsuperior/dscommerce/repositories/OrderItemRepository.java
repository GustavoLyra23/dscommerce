package com.devsuperior.dscommerce.repositories;

import com.devsuperior.dscommerce.entities.OrderItem;
import org.springframework.data.repository.CrudRepository;

public interface OrderItemRepository extends CrudRepository<OrderItem, Long> {
}
