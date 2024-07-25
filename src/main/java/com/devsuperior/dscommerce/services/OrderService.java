package com.devsuperior.dscommerce.services;

import com.devsuperior.dscommerce.dto.OrderDto;
import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderItem;
import com.devsuperior.dscommerce.entities.OrderStatus;
import com.devsuperior.dscommerce.entities.Product;
import com.devsuperior.dscommerce.repositories.OrderItemRepository;
import com.devsuperior.dscommerce.repositories.OrderRepository;
import com.devsuperior.dscommerce.repositories.ProductRepository;
import com.devsuperior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;


    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        Order result = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        return new OrderDto(result);
    }

    @Transactional
    public OrderDto insert(OrderDto orderDto) {
        Order order = new Order();
        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);
        order.setClient(userService.authenticated());
        orderDto.getItems().forEach(item -> {
            Product product = productRepository.getReferenceById(item.getProductId());
            OrderItem orderitem = new OrderItem(order, product, item.getQuantity(), product.getPrice());
            order.getOrderItems().add(orderitem);
        });

        orderRepository.save(order);
        orderItemRepository.saveAll(order.getOrderItems());

        return new OrderDto(order);
    }
}
