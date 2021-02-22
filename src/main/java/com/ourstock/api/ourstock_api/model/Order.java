package com.ourstock.api.ourstock_api.model;


import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "order_historys")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", columnDefinition = "int(10) unsigned")
    private Long orderId;

    @Column(name = "user_id", columnDefinition = "int(10) unsigned")
    private long userId;

    @Column(name = "channel_id", columnDefinition = "char(6)")
    private String channelId;

    @Column(name = "order_count", columnDefinition = "int(10) unsigned")
    private long orderCount;

    @Column(name = "trade_count", columnDefinition = "int(10) unsigned")
    @ColumnDefault("0")
    private long tradeCount;

    @Column(name = "rest_count", columnDefinition = "int(10) unsigned")
    @ColumnDefault("0")
    private long restCount;

    @Column(name = "order_cost", columnDefinition = "int(10) unsigned")
    private long orderCost;

    @Column(name = "type", columnDefinition = "varchar(4)")  // sell / buy
    private String type;

    @Column(name = "status", columnDefinition = "varchar(6)")  // stay   / cancel
    @ColumnDefault("'stay'")
    private String status;

    @Column(name = "register_time", nullable = false, updatable = false)//
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerTime;

    @Column(name = "last_change")
    private Date lastChange;

    @Column(name = "cancel_time")
    private Date cancelTime;

}
