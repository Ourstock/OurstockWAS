package com.ourstock.api.ourstock_api.handler.system;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Not Correct Password")
public class SystemException extends RuntimeException {
}
