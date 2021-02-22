package com.ourstock.api.ourstock_api.dto.user;

import lombok.Getter;

import javax.validation.constraints.NotNull;

public class UserDeleteDto {

    @Getter
    @NotNull
    private long userId;

    public UserDeleteDto() {}

    public UserDeleteDto(@NotNull long userId) {
        this.userId = userId;
    }
}
