package com.ourstock.api.ourstock_api.dto.user;


import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class UserSetAlarmDto {

    @NotNull
    private long userId;

    @NotNull
    private int alarm;

    @NotNull
    private int marketing;

    public UserSetAlarmDto(@NotNull long userId, @NotNull int alarm, @NotNull int marketing) {
        this.userId = userId;
        this.alarm = alarm;
        this.marketing = marketing;
    }

    protected UserSetAlarmDto() { super(); }


}
