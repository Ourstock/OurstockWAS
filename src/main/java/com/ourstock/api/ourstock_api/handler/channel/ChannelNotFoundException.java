package com.ourstock.api.ourstock_api.handler.channel;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Not Exist Channel")
public class ChannelNotFoundException extends RuntimeException{
}
