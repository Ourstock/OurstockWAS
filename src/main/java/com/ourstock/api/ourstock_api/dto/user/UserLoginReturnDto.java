package com.ourstock.api.ourstock_api.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Builder
@Getter
public class UserLoginReturnDto {

    private UUID userId;

    private Boolean isSuccessLogin;

    private String jwtToken;

    private String jwtRefreshToken;
}
