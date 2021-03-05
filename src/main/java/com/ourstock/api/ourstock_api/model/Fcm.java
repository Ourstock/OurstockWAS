package com.ourstock.api.ourstock_api.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "user_fcms")
public class Fcm implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fcm_id", columnDefinition = "INT(10) UNSIGNED")
    private Long fcmId;

    @Setter
    @Column(name = "user_id", columnDefinition = "BINARY(16)")
    private UUID userId;

    @Setter
    @Column(name = "fcm_token", columnDefinition = "varchar(255)")
    private String fcmToken;

//    테이블에서 TimeStamp
    @Column(name = "register_time", nullable = false, updatable = false, columnDefinition = "timestamp")//
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerTime;

//    테이블에서 Datetime timestamp를 사용시 nullable이 false로 고정되어 직접 조작할수없는단점으로 인해 변경
    @Setter
    @Column(name = "last_check_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastChecktime;

}
