package com.outstock.api.outstock_api.controller;

import com.outstock.api.outstock_api.dto.channel.ChannelRegisterDto;
import com.outstock.api.outstock_api.dto.channel.ChannelSearchNameDto;
import com.outstock.api.outstock_api.dto.channel.ChannelUpdateDto;
import com.outstock.api.outstock_api.model.Channel;
import com.outstock.api.outstock_api.service.ChannelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;

@RestController
public class ChannelController {

    private final ChannelService channelService;

    public ChannelController(ChannelService channelService) {
        this.channelService = channelService;
    }


    @PostMapping("/register/channel")
    public ResponseEntity<Channel> channelRegister(
            @Valid @RequestBody ChannelRegisterDto channelRegisterDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                channelService.registerChannel(
                        channelRegisterDto, httpServletRequest.getHeader("SystemPassword")
                )
        );
    }

    @PostMapping("/update/channel")
    public ResponseEntity<Channel> channelUpdate(
            @Valid @RequestBody ChannelUpdateDto channelUpdateDto,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(
                channelService.updateChannelInformation(
                        channelUpdateDto,
                        httpServletRequest.getHeader("SystemPassword")
                )
        );
    }

//    채널 리스트
    @GetMapping("/all/channel")
    public ArrayList<Channel> getAllChannel() {
        return channelService.allChannelInformation();
    }


//    채널 검색 (이름기준)
    @GetMapping("/search/channel/name")
    public ArrayList<Channel> getNameChannel(@Valid @RequestBody ChannelSearchNameDto channelSearchNameDto) {
        return channelService.searchChannelInformation(channelSearchNameDto);
    }

}
