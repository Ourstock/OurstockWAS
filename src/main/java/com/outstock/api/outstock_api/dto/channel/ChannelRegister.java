package com.outstock.api.outstock_api.dto.channel;

import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
public class ChannelRegister {

    @NotNull
    private String channelId;

    private String nameKor;

    private String nameEng;

    private String thumbnailUrl;

    private String country;

    private String viewCount;

    private String subscriberCount;

    private String videoCount;

    private String topicDetails;

    private String keywords;

    private String bannerExternalUrl;



}
