package com.ourstock.api.ourstock_api.handler.user;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Exist User")
public class UserSignUpException extends RuntimeException{
}