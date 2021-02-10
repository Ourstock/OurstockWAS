package com.outstock.api.outstock_api.service;


import com.outstock.api.outstock_api.model.Channel;
import com.outstock.api.outstock_api.repository.ChannelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ChannelService {

    private final ChannelRepository channelRepository;

    public ChannelService(ChannelRepository channelRepository) {
        this.channelRepository = channelRepository;
    }



}
