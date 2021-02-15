package com.outstock.api.outstock_api.controller;

import com.outstock.api.outstock_api.dto.channel.ChannelRegisterDto;
import com.outstock.api.outstock_api.dto.channel.ChannelUpdateDto;
import com.outstock.api.outstock_api.model.Channel;
import com.outstock.api.outstock_api.service.ChannelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }


    @PostMapping("/register/channel")
    public ResponseEntity<Channel> channelRegister(@Valid @RequestBody ChannelRegisterDto channelRegisterDto, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(channelService.registerChannel(channelRegisterDto, httpServletRequest.getHeader("SystemPassword")));
    }

    @PostMapping("/update/channel")
    public ResponseEntity<Channel> channelUpdate(@Valid @RequestBody ChannelUpdateDto channelUpdateDto, HttpServletRequest httpServletRequest) {
        return ResponseEntity.ok(channelService.updateChannelInformation(channelUpdateDto, httpServletRequest.getHeader("SystemPassword")));
    }

}
