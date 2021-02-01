package com.outstock.api.outstock_api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "call_number", length = 15)
    @Getter
    @Setter
    private String callNumber;

    @Column(name = "telecom", length = 10)
    @Getter
    @Setter
    private String telecom;

    @Column(name = "name", length = 10)
    @Getter
    @Setter
    private String username;

    @Column(name = "jwt_token")
    @Getter
    @Setter
    private String jwtToken;

    @Column(name = "account_name", length = 10)
    @Getter
    @Setter
    private String accountName;

    @Column(name = "account_number", length = 20)
    @Getter
    @Setter
    private String accountNumber;

    @Column(name = "account_bank", length = 10)
    @Getter
    @Setter
    private String accountBank;

    @Column(name = "resident_registration_number_front", length = 6)
    @Getter
    @Setter
    private String residentRegistrationNumberFront;

    @Column(name = "resident_registration_number_back", length = 7)
    @Getter
    @Setter
    private String residentRegistrationNumberBack;

    @Column(name = "city_name", length = 15)
    @Getter
    @Setter
    private String cityName;

    @Column(name = "district_name", length = 15)
    @Getter
    @Setter
    private String districtName;

    @Column(name = "dong_name", length = 15)
    @Getter
    @Setter
    private String dongName;

    @Column(name = "road_name", length = 30)
    @Setter
    @Getter
    private String roadName;

    @Column(name = "main_building_number", length = 10)
    @Getter
    @Setter
    private String mainBuildingNumber;

    @Column(name = "sub_building_number", length = 10)
    @Getter
    @Setter
    private String subBuildingNumber;

    @Column(name = "building_name", length = 20)
    @Getter
    @Setter
    private String buildingName;

    @Column(name = "additional_address", length = 30)
    @Getter
    @Setter
    private String additionalAddress;


    @Column(name = "post_code", length = 10)
    @Getter
    @Setter
    private String postCode;

    @Column(name = "alarm")
    @Getter
    @Setter
    private int alarm;

    @Column(name = "marketing")
    @Getter
    @Setter
    private int marketing;

    @Column(name = "email_address")
    @Getter
    @Setter
    private String emailAddress;

    @Column(name = "register_time", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerTime;

    @Column(name = "last_connection")
    @Getter
    @Setter
    private Date lastConnection;

    @Column(name = "os", length = 10)
    @Getter
    @Setter
    private String os;

    @Column(name = "application_version", length = 10)
    @Getter
    @Setter
    private String applicationVersion;

    @Column(name = "device_model", length = 25)
    @Getter
    @Setter
    private String deviceModel;

    @Column(name = "invest_limit")
    @Getter
    @Setter
    private Integer investLimit;

    @Column(name = "deposit")
    @Getter
    @Setter
    private Integer deposit;


    @Builder
    public UserEntity(String callNumber, String telecom, String username, String jwtToken, String accountName, String accountNumber, String accountBank, String residentRegistrationNumberFront, String residentRegistrationNumberBack, String cityName, String districtName, String dongName, String roadName, String mainBuildingNumber, String subBuildingNumber, String buildingName, String additionalAddress, String postCode, int alarm, int marketing, String emailAddress, Date registerTime, Date lastConnection, String os, String applicationVersion, String deviceModel, Integer investLimit, Integer deposit) {
        this.callNumber = callNumber;
        this.telecom = telecom;
        this.username = username;
        this.jwtToken = jwtToken;
        this.accountName = accountName;
        this.accountNumber = accountNumber;
        this.accountBank = accountBank;
        this.residentRegistrationNumberFront = residentRegistrationNumberFront;
        this.residentRegistrationNumberBack = residentRegistrationNumberBack;
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
        this.emailAddress = emailAddress;
        this.registerTime = registerTime;
        this.lastConnection = lastConnection;
        this.os = os;
        this.applicationVersion = applicationVersion;
        this.deviceModel = deviceModel;
        this.investLimit = investLimit;
        this.deposit = deposit;
    }


    public UserEntity() {}


}
