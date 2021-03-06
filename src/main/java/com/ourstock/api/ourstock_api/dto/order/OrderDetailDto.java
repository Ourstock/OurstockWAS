package com.ourstock.api.ourstock_api.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrderDetailDto {

    @NotNull
    private long orderId;

    @NotNull
    private long userId;

    public OrderDetailDto(@NotNull long orderId, @NotNull long userId) {
        this.orderId = orderId;
        this.userId = userId;
    }
}
