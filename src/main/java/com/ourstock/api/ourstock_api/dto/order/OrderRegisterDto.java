package com.ourstock.api.ourstock_api.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
public class OrderRegisterDto {

    @NotNull
    private UUID userId;

    @NotNull
    private String channelId;

    @NotNull
    private long orderCount;

    @NotNull
    private long orderCost;

    @NotNull
    private String type;

    protected OrderRegisterDto() { super(); }

    public OrderRegisterDto(@NotNull UUID userId, @NotNull String channelId, @NotNull long orderCount, @NotNull long orderCost, @NotNull String type) {
        this.userId = userId;
        this.channelId = channelId;
        this.orderCount = orderCount;
        this.orderCost = orderCost;
        this.type = type;
    }
}
