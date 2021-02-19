package com.outstock.api.outstock_api.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrderRegisterDto {

    @NotNull
    private int userId;

    @NotNull
    private String channelId;

    @NotNull
    private int orderCount;

    @NotNull
    private int orderCost;

    @NotNull
    private String type;

    protected OrderRegisterDto() { super(); }

    public OrderRegisterDto(@NotNull int userId, @NotNull String channelId, @NotNull int orderCount, @NotNull int orderCost, @NotNull String type) {
        this.userId = userId;
        this.channelId = channelId;
        this.orderCount = orderCount;
        this.orderCost = orderCost;
        this.type = type;
    }
}
