package com.ourstock.api.ourstock_api.model;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name = "trade_historys")
public class Trade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trade_id", columnDefinition = "int(10) unsigned")
    private Long tradeId;

    @Column(name = "buy_order_id", columnDefinition = "int(10) unsigned")
    private long buyOrderId;

    @Column(name = "buy_user_id", columnDefinition = "int(10) unsigned")
    private UUID buyUserId;

    @Column(name = "sell_order_id", columnDefinition = "int(10) unsigned")
    private long sellOrderId;

    @Column(name = "sell_user_id", columnDefinition = "int(10) unsigned")
    private UUID sellUserId;

    @Column(name = "channel_id", columnDefinition = "char(6)")
    private String channelId;

    @Column(name = "trade_count", columnDefinition = "int(10) unsigned")
    @ColumnDefault("0")
    private long tradeCount;

    @Column(name = "trade_cost", columnDefinition = "int(10) unsigned")
    private long tradeCost;

    @Column(name = "trade_cost_sum", columnDefinition = "int(10) unsigned")
    private long tradeCostSum;

    @Column(name = "fee", columnDefinition = "int(10) unsigned")
    private int fee;

    @Column(name = "settlement_amount", columnDefinition = "int(10) unsigned")
    private long settlementAmount;

    @Column(name = "last_type", columnDefinition = "varchar(4)")
    private String lastType;

    @Column(name = "register_time", nullable = false, updatable = false)//
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerTime;



}
