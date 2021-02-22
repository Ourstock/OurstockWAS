package com.ourstock.api.ourstock_api.handler.user;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Unauthorized")
public class JwtTokenHandler extends RuntimeException{
}
