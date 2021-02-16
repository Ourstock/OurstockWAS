package com.outstock.api.outstock_api.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class OrderDeleteDto {

    @NotNull
    private int orderId;

    @NotNull
    private int userId;

    @NotNull
    private String channelId;

    @NotNull
    private int orderCount;

    @NotNull
    private int tradeCount;

    @NotNull
    private int restCount;

    @NotNull
    private int orderCost;

    @NotNull
    private String type;

    @NotNull
    private String status;

    public OrderDeleteDto() { super(); }

    public OrderDeleteDto(@NotNull int userId, @NotNull String channelId, @NotNull int orderCount, @NotNull int tradeCount, @NotNull int restCount, @NotNull int orderCost, @NotNull String type, @NotNull String status) {
        this.userId = userId;
        this.channelId = channelId;
        this.orderCount = orderCount;
        this.tradeCount = tradeCount;
        this.restCount = restCount;
        this.orderCost = orderCost;
        this.type = type;
        this.status = status;
    }
}
