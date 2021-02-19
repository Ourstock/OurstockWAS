package com.outstock.api.outstock_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "trade_historys")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id", columnDefinition = "int(10) unsigned")
    private Long tradeId;

    @Column(name = "buy_order_id", columnDefinition = "int(10) unsigned")
    private int buyOrderId;

    @Column(name = "buy_user_id", columnDefinition = "int(10) unsigned")
    private int buyUserId;

    @Column(name = "sell_order_id", columnDefinition = "int(10) unsigned")
    private int sellOrderId;

    @Column(name = "sell_user_id", columnDefinition = "int(10) unsigned")
    private int sellUserId;

    @Column(name = "channel_id", columnDefinition = "char(6)")
    private String channelId;

    @Column(name = "trade_count", columnDefinition = "int(10) unsigned")
    @ColumnDefault("0")
    private int tradeCount;

    @Column(name = "trade_cost", columnDefinition = "int(10) unsigned")
    private int tradeCost;

    @Column(name = "trade_cost_sum", columnDefinition = "int(10) unsigned")
    private int tradeCostSum;

    @Column(name = "fee", columnDefinition = "int(10) unsigned zerofill")
    private int fee;

    @Column(name = "settlement_amount", columnDefinition = "int(10) unsigned")
    private int settlementAmount;

    @Column(name = "last_type", columnDefinition = "varchar(4)")
    private String lastType;

    @Column(name = "register_time", nullable = false, updatable = false)//
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerTime;

    public void setBuyOrderId(int buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public void setBuyUserId(int buyUserId) {
        this.buyUserId = buyUserId;
    }

    public void setSellOrderId(int sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public void setSellUserId(int sellUserId) {
        this.sellUserId = sellUserId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public void setTradeCost(int tradeCost) {
        this.tradeCost = tradeCost;
    }

    public void setTradeCostSum(int tradeCostSum) {
        this.tradeCostSum = tradeCostSum;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setSettlementAmount(int settlementAmount) {
        this.settlementAmount = settlementAmount;
    }

    public void setLastType(String lastType) {
        this.lastType = lastType;
    }
}
