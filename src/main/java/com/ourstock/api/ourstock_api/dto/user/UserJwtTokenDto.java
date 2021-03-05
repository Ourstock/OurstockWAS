package com.ourstock.api.ourstock_api.dto.user;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Builder
public class UserJwtTokenDto {


    private UUID userId;

    @Setter
    private String jwtToken;

    @Setter
    private String jwtRefreshToken;


}
