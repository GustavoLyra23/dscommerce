package com.devsuperior.dscommerce.dto;

import com.devsuperior.dscommerce.entities.Payment;

import java.time.Instant;

public class PaymentDto {

    private Long id;
    private Instant moment;

    public PaymentDto(Payment entity) {
        this.id = entity.getId();
        this.moment = entity.getMoment();
    }


    public PaymentDto(Long id, Instant moment) {
        this.id = id;
        this.moment = moment;
    }

    public Long getId() {
        return id;
    }

    public Instant getMoment() {
        return moment;
    }
}
