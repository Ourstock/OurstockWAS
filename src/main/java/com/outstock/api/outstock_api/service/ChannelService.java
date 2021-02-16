package com.outstock.api.outstock_api.service;


import com.outstock.api.outstock_api.dto.channel.ChannelRegisterDto;
import com.outstock.api.outstock_api.dto.channel.ChannelUpdateDto;
import com.outstock.api.outstock_api.handler.channel.ChannelExistHandler;
import com.outstock.api.outstock_api.handler.channel.ChannelNotFoundHandler;
import com.outstock.api.outstock_api.handler.system.SystemHandler;
import com.outstock.api.outstock_api.model.Channel;
import com.outstock.api.outstock_api.repository.ChannelRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    @Value("${systempassword}")
    private String systemPassword;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }


    @Transactional
    public Channel registerChannel(ChannelRegisterDto channelRegisterDto, String inputPassword) {
        if (inputPassword.equals(this.systemPassword)) {
            if (channelRepository.findById(channelRegisterDto.getChannelId()).isEmpty()) {
                Channel channel = Channel.builder()
                        .channelId(channelRegisterDto.getChannelId())
                        .nameKor(channelRegisterDto.getNameKor())
                        .nameEng(channelRegisterDto.getNameEng())
                        .thumbnailUrl(channelRegisterDto.getThumbnailUrl())
                        .country(channelRegisterDto.getCountry())
                        .viewCount(channelRegisterDto.getViewCount())
                        .subscriberCount(channelRegisterDto.getSubscriberCount())
                        .hiddenSubscriberCount(channelRegisterDto.getHiddenSubscriberCount())
                        .videoCount(channelRegisterDto.getVideoCount())
                        .topicDetails(channelRegisterDto.getTopicDetails())
                        .keyWords(channelRegisterDto.getTopicDetails())
                        .bannerExternalUrl(channelRegisterDto.getBannerExternalUrl())
                        .status(channelRegisterDto.getStatus())
                        .build();
                return channelRepository.save(channel);
            }
            throw new ChannelExistHandler();
        }
        throw new SystemHandler();
    }

    @Transactional
    public Channel updateChannelInformation(ChannelUpdateDto channelUpdateDto, String inputPassword) {
        if (inputPassword.equals(systemPassword)){
            if (channelRepository.findById(channelUpdateDto.getChannelId()).isPresent()) {
                Channel channel = channelRepository.getOne(channelUpdateDto.getChannelId());
                Date now = new Date();
                channel.setNameKor(channelUpdateDto.getNameKor());
                channel.setNameEng(channelUpdateDto.getNameEng());
                channel.setThumbnailUrl(channelUpdateDto.getThumbnailUrl());
                channel.setViewCount(channelUpdateDto.getViewCount());
                channel.setSubscriberCount(channelUpdateDto.getSubscriberCount());
                channel.setHiddenSubscriberCount(channelUpdateDto.getHiddenSubscriberCount());
                channel.setVideoCount(channelUpdateDto.getVideoCount());
                channel.setTopicDetails(channelUpdateDto.getTopicDetails());
                channel.setKeyWords(channelUpdateDto.getKeywords());
                channel.setBannerExternalUrl(channelUpdateDto.getBannerExternalUrl());
                channel.setStatus(channelUpdateDto.getStatus());
                if (channelUpdateDto.getStatus() == 1) {
                    channel.setListingTime(now);
                } else if (channelUpdateDto.getStatus() == 2) {
                    channel.setOnstockTime(now);
                } else if (channelUpdateDto.getStatus() == 3) {
                    channel.setOfferingTimeStart(now);
                } else if (channelUpdateDto.getStatus() == 4) {
                    channel.setOfferingTimeEnd(now);
                } else if (channelUpdateDto.getStatus() == 5) {
                    channel.setTradingStopTime(now);
                } else if (channelUpdateDto.getStatus() == 6) {
                    channel.setDelistingTime(now);
                }
                return channelRepository.save(channel);
            }
            throw new ChannelNotFoundHandler();
        }
        throw new SystemHandler();
    }

    @Transactional
    public boolean validChannelId(String channelId) {
        return channelRepository.findById(channelId).isPresent();
    }

    @Transactional
    public ArrayList<Channel> allChannelInformation() {
        return new ArrayList<Channel>(channelRepository.findAll());
    }

    @Transactional
    public ArrayList<Channel> searchChannelInformation(long subscriberCount) {
        return new ArrayList<Channel>(channelRepository.findAllBySubscriberCount(subscriberCount));
    }

}
