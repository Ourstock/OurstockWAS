package com.ourstock.api.ourstock_api.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrderUpdateDto {

    @NotNull
    private long orderId;

    @NotNull
    private long userId;

    @NotNull
    private String channelId;

    @NotNull
    private long orderCount;

    @NotNull
    private long orderCost;

    @NotNull
    private String type;

    public OrderUpdateDto(@NotNull long orderId, @NotNull long userId, @NotNull String channelId, @NotNull long orderCount, @NotNull long orderCost, @NotNull String type) {
        this.orderId = orderId;
        this.userId = userId;
        this.channelId = channelId;
        this.orderCount = orderCount;
        this.orderCost = orderCost;
        this.type = type;
    }
}
