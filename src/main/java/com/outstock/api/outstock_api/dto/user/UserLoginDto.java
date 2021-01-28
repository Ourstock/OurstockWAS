package com.outstock.api.outstock_api.dto.user;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

public class UserLoginDto {

    @NotNull
    @Getter
    private String callNumber;

    @Builder
    public UserLoginDto(@NotNull String callNumber) {
        this.callNumber = callNumber;
    }

    public UserLoginDto() {}
}
