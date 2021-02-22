package com.ourstock.api.ourstock_api.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
@Table(name = "channels")
public class Channel {

    @Id
    @Column(name = "channel_id", columnDefinition = "char(6)")
    private String channelId;

    @Column(name = "name_kor", columnDefinition = "varchar(100)")
    private String nameKor;

    @Column(name = "name_eng", columnDefinition = "varchar(100)")
    private String nameEng;

    @Column(name = "thumbnail_url", columnDefinition = "text")
    private String thumbnailUrl;

    @Column(name = "country", columnDefinition = "varchar(100)")
    private String country;

    @Column(name = "view_count", columnDefinition = "int(11) unsigned")
    private int viewCount;

    @Column(name = "subscriber_count", columnDefinition = "int(11) unsigned")
    private int subscriberCount;

    @Column(name = "hidden_subscriber_count", columnDefinition = "tinyint(1) unsigned")
    private int hiddenSubscriberCount;

    @Column(name = "video_count", columnDefinition = "int(11) unsigned")
    private int videoCount;

    @Column(name = "topic_details", columnDefinition = "text")
    private String topicDetails;

    @Column(name = "keywords", columnDefinition = "text")
    private String keyWords;

    @Column(name = "banner_external_url", columnDefinition = "text")
    private String bannerExternalUrl;

    @Column(name = "register_time", nullable = false, updatable = false, columnDefinition = "timestamp")//
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date registerTime;

    @Column(name = "status", columnDefinition = "tinyint(1)")
    private int status;

    @Column(name = "listing_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date listingTime;

    @Column(name = "onstock_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date onstockTime;

    @Column(name = "offering_time_start")
    @Temporal(TemporalType.TIMESTAMP)
    private Date offeringTimeStart;

    @Column(name = "offering_time_end")
    @Temporal(TemporalType.TIMESTAMP)
    private Date offeringTimeEnd;

    @Column(name = "trading_stop_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tradingStopTime;

    @Column(name = "delisting_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date delistingTime;


    public void setNameKor(String nameKor) {
        this.nameKor = nameKor;
    }

    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setSubscriberCount(int subscriberCount) {
        this.subscriberCount = subscriberCount;
    }

    public void setHiddenSubscriberCount(int hiddenSubscriberCount) {
        this.hiddenSubscriberCount = hiddenSubscriberCount;
    }

    public void setVideoCount(int videoCount) {
        this.videoCount = videoCount;
    }

    public void setTopicDetails(String topicDetails) {
        this.topicDetails = topicDetails;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public void setBannerExternalUrl(String bannerExternalUrl) {
        this.bannerExternalUrl = bannerExternalUrl;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setListingTime(Date listingTime) {
        this.listingTime = listingTime;
    }

    public void setOnstockTime(Date onstockTime) {
        this.onstockTime = onstockTime;
    }

    public void setOfferingTimeStart(Date offeringTimeStart) {
        this.offeringTimeStart = offeringTimeStart;
    }

    public void setOfferingTimeEnd(Date offeringTimeEnd) {
        this.offeringTimeEnd = offeringTimeEnd;
    }

    public void setTradingStopTime(Date tradingStopTime) {
        this.tradingStopTime = tradingStopTime;
    }

    public void setDelistingTime(Date delistingTime) {
        this.delistingTime = delistingTime;
    }
}