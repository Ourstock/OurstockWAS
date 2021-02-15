package com.outstock.api.outstock_api.handler.channel;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Exist Channel Id")
public class ChannelExistHandler extends RuntimeException{
}
