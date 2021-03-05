package com.ourstock.api.ourstock_api.dto.user;


import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

@Getter
public class UserFcmTokenDto {

    @NotNull
    private UUID userId;

    @NotNull
    private String fcmToken;

    @Setter
    private Date lastCheckTime;

    public UserFcmTokenDto(@NotNull UUID userId, @NotNull String fcmToken, Date lastCheckTime) {
        this.userId = userId;
        this.fcmToken = fcmToken;
        this.lastCheckTime = lastCheckTime;
    }
}
