package com.ourstock.api.ourstock_api.dto.user;


import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserSignUpDto {

    @NotNull
    private String callNumber;

    @NotNull
    private String telecom;

    @NotNull
    private String username;

    @NotNull
    private String residentRegistrationNumberFront;

    @NotNull
    private String residentRegistrationNumberBack;

    @NotNull
    private int alarm;

    protected UserSignUpDto() {
        super();
    }

    public UserSignUpDto(@NotNull String callNumber, @NotNull String telecom, @NotNull String username, @NotNull String residentRegistrationNumberFront, @NotNull String residentRegistrationNumberBack,
                         @NotNull int alarm) {
        this.callNumber = callNumber;
        this.telecom = telecom;
        this.username = username;
        this.residentRegistrationNumberFront = residentRegistrationNumberFront;
        this.residentRegistrationNumberBack = residentRegistrationNumberBack;
        this.alarm = alarm;
    }


}