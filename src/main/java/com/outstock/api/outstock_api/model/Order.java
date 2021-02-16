package com.outstock.api.outstock_api.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order_historys")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", columnDefinition = "int(10) unsigned")
    private Long orderId;

    @Column(name = "user_id", columnDefinition = "int(10) unsigned")
    private int userId;

    @Column(name = "channel_id", columnDefinition = "char(6)")
    private String channelId;

    @Column(name = "order_count", columnDefinition = "int(10) unsigned")
    private int orderCount;

    @Column(name = "trade_count", columnDefinition = "int(10) unsigned")
    private int tradeCount;

    @Column(name = "rest_count", columnDefinition = "int(10) unsigned")
    private int restCount;

    @Column(name = "order_cost", columnDefinition = "int(10) unsigned")
    private int orderCost;

    @Column(name = "type", columnDefinition = "varchar(4)")  // sell / buy
    private String type;

    @Column(name = "status", columnDefinition = "varchar(6)")  // stay   / cancel
    private String status;

    @Column(name = "register_time", nullable = false, updatable = false)//
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerTime;

    @Column(name = "last_change")
    private Date lastChange;

    @Column(name = "cancel_time")
    private Date cancelTime;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public void setOrderCount(int orderCount) {
        this.orderCount = orderCount;
    }

    public void setTradeCount(int tradeCount) {
        this.tradeCount = tradeCount;
    }

    public void setRestCount(int restCount) {
        this.restCount = restCount;
    }

    public void setOrderCost(int orderCost) {
        this.orderCost = orderCost;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setLastChange(Date lastChange) {
        this.lastChange = lastChange;
    }

    public void setCancelTime(Date cancelTime) {
        this.cancelTime = cancelTime;
    }
}
