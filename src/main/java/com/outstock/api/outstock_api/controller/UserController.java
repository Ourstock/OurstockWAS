package com.outstock.api.outstock_api.controller;


import com.outstock.api.outstock_api.dto.user.UserFcmTokenDto;
import com.outstock.api.outstock_api.dto.user.UserLoginDto;
import com.outstock.api.outstock_api.dto.user.UserSignUpDto;
import com.outstock.api.outstock_api.model.Fcm;
import com.outstock.api.outstock_api.model.UserEntity;
import com.outstock.api.outstock_api.service.UserService;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    회원가입
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(
            @Valid @RequestBody UserSignUpDto userSignUpDto
    ) {
        return ResponseEntity.ok(userService.signup(userSignUpDto));
    }

//    로그인
    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUser(
            @Valid @RequestBody UserLoginDto userLoginDto
    ) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

//    Fcm토큰 저장
    @PostMapping("/user/fcm")
    public ResponseEntity<Fcm> userFcmToken(
            @Valid @RequestBody UserFcmTokenDto userFcmTokenDto,
            HttpServletRequest httpServletRequest
    ) {
        String jwtToken = httpServletRequest.getHeader("JwtAccessToken");
        return ResponseEntity.ok(userService.saveUserFcmToken(userFcmTokenDto, jwtToken));
    }
}
