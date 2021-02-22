package com.ourstock.api.ourstock_api.dto.user;


import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class UserFcmTokenDto {

    @NotNull
    private long userId;

    @NotNull
    private String fcmToken;

    private Date lastCheckTime;

    public UserFcmTokenDto(@NotNull long userId, @NotNull String fcmToken, Date lastCheckTime) {
        this.userId = userId;
        this.fcmToken = fcmToken;
        this.lastCheckTime = lastCheckTime;
    }
}
