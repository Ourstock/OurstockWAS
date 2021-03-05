package com.ourstock.api.ourstock_api.dto.user;

import lombok.Builder;
import lombok.Getter;

import java.util.UUID;

@Builder
@Getter
public class UserRefreshToken {

    private final UUID userId;
    private final String jwtRefreshToken;

}
