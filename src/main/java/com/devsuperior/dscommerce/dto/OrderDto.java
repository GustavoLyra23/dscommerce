package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Order;
import com.devsuperior.dscommerce.entities.OrderStatus;
import jakarta.validation.constraints.NotEmpty;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private Long id;
    private Instant moment;
    private OrderStatus status;

    private ClientDto client;
    private PaymentDto payment;

    @NotEmpty(message = "deve ter pelo menos um orderItem")
    private List<OrderItemDto> items = new ArrayList<OrderItemDto>();

    public OrderDto(Order entity) {
        id = entity.getId();
        moment = entity.getMoment();
        status = entity.getStatus();
        client = new ClientDto(entity.getClient());
        payment = (entity.getPayment() == null) ? null : new PaymentDto(entity.getPayment());
        entity.getOrderItems().forEach(orderItem -> {
            items.add(new OrderItemDto(orderItem));
        });
    }


    public OrderDto(Long id, Instant moment, OrderStatus status, ClientDto client, PaymentDto payment) {
        this.id = id;
        this.moment = moment;
        this.status = status;
        this.client = client;
        this.payment = payment;
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ClientDto getClient() {
        return client;
    }

    public PaymentDto getPayment() {
        return payment;
    }

    public List<OrderItemDto> getItems() {
        return items;
    }

    public Double getTotal() {
        double sum = 0;
        for (OrderItemDto orderItem : items) {
            sum += orderItem.getPrice();
        }
        return sum;
    }

}
