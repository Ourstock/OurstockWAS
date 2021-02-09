package com.outstock.api.outstock_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Getter
@Entity
@Table(name = "userss")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", columnDefinition = "INT(11) UNSIGNED")
    private Long userId;

    @Setter
    @Column(name = "call_number", length = 15)
    private String callNumber;

    @Setter
    @Column(name = "telecom", length = 10)
    private String telecom;

    @Setter
    @Column(name = "name", length = 20)
    private String username;

    @Setter
    @Column(name = "account_name", length = 10)
    private String accountName;

    @Setter
    @Column(name = "account_number", length = 20)
    private String accountNumber;

    @Setter
    @Column(name = "account_bank", length = 10)
    private String accountBank;

    @Setter
    @Column(name = "resident_registration_number_front", length = 6)
    private String residentRegistrationNumberFront;

    @Setter
    @Column(name = "resident_registration_number_back", length = 7)
    private String residentRegistrationNumberBack;

    @Setter
    @Column(name = "jwt_token")
    private String jwtToken;

    @Setter
    @Column(name = "city_name", length = 15)
    private String cityName;

    @Setter
    @Column(name = "district_name", length = 15)
    private String districtName;

    @Setter
    @Column(name = "dong_name", length = 15)
    private String dongName;

    @Setter
    @Column(name = "road_name", length = 30)
    private String roadName;

    @Setter
    @Column(name = "main_building_number", length = 10)
    private String mainBuildingNumber;

    @Setter
    @Column(name = "sub_building_number", length = 10)
    private String subBuildingNumber;

    @Setter
    @Column(name = "building_name", length = 20)
    private String buildingName;

    @Setter
    @Column(name = "additional_address", length = 30)
    private String additionalAddress;

    @Setter
    @Column(name = "post_code", length = 10)
    private String postCode;

    @Setter
    @Column(name = "alarm", columnDefinition = "TINYINT(1) UNSIGNED")
    private int alarm;

    @Setter
    @Column(name = "marketing", columnDefinition = "TINYINT(1) UNSIGNED")
    private int marketing;

    @Setter
    @Column(name = "register_time", nullable = false, updatable = false, columnDefinition = "timestamp")//
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerTime;

    @Setter
    @Column(name = "last_connection")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastConnection;

    @Setter
    @Column(name = "os", length = 10)
    private String os;

    @Setter
    @Column(name = "application_version", length = 10)
    private String applicationVersion;

    @Setter
    @Column(name = "device_model", length = 25)
    private String deviceModel;

    @Setter
    @Column(name = "device_manufacturer", length = 15)
    private String deviceManufacturer;

    @Setter
    @Column(name = "invest_limit", columnDefinition = "INT(11) UNSIGNED")
    private int investLimit;

    @Setter
    @Column(name = "deposit", columnDefinition = "INT(11) UNSIGNED")
    private int deposit;

    @Builder
    public UserEntity(Long userId, String callNumber, String telecom, String username, String accountName, String accountNumber, String accountBank, String residentRegistrationNumberFront, String residentRegistrationNumberBack, String jwtToken, String cityName, String districtName, String dongName, String roadName, String mainBuildingNumber, String subBuildingNumber, String buildingName, String additionalAddress, String postCode, int alarm, int marketing, Date registerTime, Date lastConnection, String os, String applicationVersion, String deviceModel, String deviceManufacturer, int investLimit, int deposit) {
        this.userId = userId;
        this.callNumber = callNumber;
        this.telecom = telecom;
        this.username = username;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBank = accountBank;
        this.residentRegistrationNumberFront = residentRegistrationNumberFront;
        this.residentRegistrationNumberBack = residentRegistrationNumberBack;
        this.jwtToken = jwtToken;
        this.cityName = cityName;
        this.districtName = districtName;
        this.dongName = dongName;
        this.roadName = roadName;
        this.mainBuildingNumber = mainBuildingNumber;
        this.subBuildingNumber = subBuildingNumber;
        this.buildingName = buildingName;
        this.additionalAddress = additionalAddress;
        this.postCode = postCode;
        this.alarm = alarm;
        this.marketing = marketing;
        this.registerTime = registerTime;
        this.lastConnection = lastConnection;
        this.os = os;
        this.applicationVersion = applicationVersion;
        this.deviceModel = deviceModel;
        this.deviceManufacturer = deviceManufacturer;
        this.investLimit = investLimit;
        this.deposit = deposit;
    }

    protected UserEntity() { super(); }


}
