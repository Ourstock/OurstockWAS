package com.outstock.api.outstock_api.dto.channel;

import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
public class ChannelUpdateDto {

    @NotNull
    private String channelId;

    private String nameKor;

    private String nameEng;

    private String thumbnailUrl;

    private String country;

    private int viewCount;

    private int subscriberCount;

    private int hiddenSubscriberCount;

    private int videoCount;

    private String topicDetails;

    private String keywords;

    private String bannerExternalUrl;

    private int status;

    private Date onStockTime;
    private Date offeringTimeEnd;
    private Date tradingStopTime;
    private Date delistingTime;

    public ChannelUpdateDto() { super(); }

    public ChannelUpdateDto(@NotNull String channelId, String nameKor, String nameEng, String thumbnailUrl, String country, int viewCount, int subscriberCount, int hiddenSubscriberCount, int videoCount, String topicDetails, String keywords, String bannerExternalUrl, int status, Date onStockTime, Date offeringTimeEnd, Date tradingStopTime, Date delistingTime) {
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
        this.onStockTime = onStockTime;
        this.offeringTimeEnd = offeringTimeEnd;
        this.tradingStopTime = tradingStopTime;
        this.delistingTime = delistingTime;
    }
}
