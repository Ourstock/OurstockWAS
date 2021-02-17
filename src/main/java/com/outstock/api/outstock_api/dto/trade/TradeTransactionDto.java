package com.outstock.api.outstock_api.dto.trade;


import lombok.Getter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

@Getter
public class TradeTransactionDto {

    @NotNull
    private int buyOrderId;

    @NotNull
    private int buyUserId;

    @NotNull
    private int sellOrderId;

    @NotNull
    private int sellUserId;

    @NotNull
    private String channelId;

    @NotNull
    private int tradeCount;

    @NotNull
    private int tradeCost;

    @NotNull
    private String lastType;

    public TradeTransactionDto(@NotNull int buyOrderId, @NotNull int buyUserId, @NotNull int sellOrderId, @NotNull int sellUserId, @NotNull String channelId, @NotNull int tradeCount, @NotNull int tradeCost, @NotNull String lastType) {
        this.buyOrderId = buyOrderId;
        this.buyUserId = buyUserId;
        this.sellOrderId = sellOrderId;
        this.sellUserId = sellUserId;
        this.channelId = channelId;
        this.tradeCount = tradeCount;
        this.tradeCost = tradeCost;
        this.lastType = lastType;
    }
}
