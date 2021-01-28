package com.outstock.api.outstock_api.handler.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Exist User")
public class UserSignUpHandler extends RuntimeException{
}