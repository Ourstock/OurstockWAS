package com.ourstock.api.ourstock_api.dto.order;

import lombok.Getter;

import javax.validation.constraints.NotNull;

public class OrderListDto {

    @Getter
    @NotNull
    private String channelId;

    public OrderListDto() {}

    public OrderListDto(@NotNull String channelId) {
        this.channelId = channelId;
    }
}
