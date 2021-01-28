package com.outstock.api.outstock_api.dto.user;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserSignUpDto {

    @NotNull
    private String callNumber;

    @NotNull
    private String username;

    private String telecom;
    private String cityName;
    private String districtName;
    private String dongName;
    private String roadName;
    private String mainBuildingNumber;
    private String subBuildingNumber;
    private String buildingName;
    private String addtionalAddress;
    private String postCode;
    private Integer alarm;
    private Integer marketing;


    @Builder
    public UserSignUpDto(@NotNull String callNumber, @NotNull String username, String telecom, String cityName, String districtName, String dongName, String roadName, String mainBuildingNumber, String subBuildingNumber, String buildingName, String addtionalAddress, String postCode, Integer alarm, Integer marketing) {
        this.callNumber = callNumber;
        this.username = username;
        this.telecom = telecom;
        this.cityName = cityName;
        this.districtName = districtName;
        this.dongName = dongName;
        this.roadName = roadName;
        this.mainBuildingNumber = mainBuildingNumber;
        this.subBuildingNumber = subBuildingNumber;
        this.buildingName = buildingName;
        this.addtionalAddress = addtionalAddress;
        this.postCode = postCode;
        this.alarm = alarm;
        this.marketing = marketing;
    }
}