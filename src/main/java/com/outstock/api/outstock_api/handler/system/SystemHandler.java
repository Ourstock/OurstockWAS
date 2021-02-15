package com.outstock.api.outstock_api.handler.system;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "Not Correct Password")
public class SystemHandler extends RuntimeException {
}
