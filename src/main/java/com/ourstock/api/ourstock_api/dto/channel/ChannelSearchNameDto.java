package com.ourstock.api.ourstock_api.dto.channel;

import lombok.Getter;

@Getter
public class ChannelSearchNameDto {

    private String channelName;


    public ChannelSearchNameDto() { super(); }

    public ChannelSearchNameDto(String channelName) {
        this.channelName = channelName;
    }
}
