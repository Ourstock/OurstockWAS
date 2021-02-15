package com.outstock.api.outstock_api.dto.channel;

import lombok.Getter;

import javax.validation.constraints.NotNull;


@Getter
public class ChannelRegisterDto {

    private String channelId;

    private String nameKor;

    private String nameEng;

    private String thumbnailUrl;

    private String country;

    private Integer viewCount;

    private Integer subscriberCount;

    private Integer hiddenSubscriberCount;

    private Integer videoCount;

    private String topicDetails;

    private String keywords;

    private String bannerExternalUrl;

    private Integer status;

    public ChannelRegisterDto() { super(); }

    public ChannelRegisterDto(String channelId, String nameKor, String nameEng, String thumbnailUrl, String country, Integer viewCount, Integer subscriberCount, Integer hiddenSubscriberCount, Integer videoCount, String topicDetails, String keywords, String bannerExternalUrl, Integer status) {
        this.channelId = channelId;
        this.nameKor = nameKor;
        this.nameEng = nameEng;
        this.thumbnailUrl = thumbnailUrl;
        this.country = country;
        this.viewCount = viewCount;
        this.subscriberCount = subscriberCount;
        this.hiddenSubscriberCount = hiddenSubscriberCount;
        this.videoCount = videoCount;
        this.topicDetails = topicDetails;
        this.keywords = keywords;
        this.bannerExternalUrl = bannerExternalUrl;
        this.status = status;
    }
}
