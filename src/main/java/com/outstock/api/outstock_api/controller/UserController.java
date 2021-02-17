package com.outstock.api.outstock_api.controller;


import com.outstock.api.outstock_api.dto.user.UserFcmTokenDto;
import com.outstock.api.outstock_api.dto.user.UserLoginDto;
import com.outstock.api.outstock_api.dto.user.UserSetAlarmDto;
import com.outstock.api.outstock_api.dto.user.UserSignUpDto;
import com.outstock.api.outstock_api.handler.user.JwtTokenHandler;
import com.outstock.api.outstock_api.handler.user.UserNotFoundHandler;
import com.outstock.api.outstock_api.handler.user.UserSignUpHandler;
import com.outstock.api.outstock_api.model.Fcm;
import com.outstock.api.outstock_api.model.UserEntity;
import com.outstock.api.outstock_api.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiOperation(value = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "Exist User", response = UserSignUpHandler.class)
    })
    @PostMapping("/register")
    public ResponseEntity<UserEntity> registerUser(
            @Valid @RequestBody UserSignUpDto userSignUpDto
    ) {
        return ResponseEntity.ok(userService.signup(userSignUpDto));
    }

//    유저 알람 세팅
    @ApiOperation(value = "알람설정")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Exist User", response = UserNotFoundHandler.class)
    })
    @PostMapping("/user/alarm")
    public ResponseEntity<UserEntity> setUserAlarm(
            @Valid @RequestBody UserSetAlarmDto userSetAlarmDto
    ) {
        return ResponseEntity.ok(userService.setAalarm(userSetAlarmDto));
    }

//    로그인
    @ApiOperation(value = "로그인")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Exist User", response = UserNotFoundHandler.class)
    })
    @PostMapping("/login")
    public ResponseEntity<UserEntity> loginUser(
            @Valid @RequestBody UserLoginDto userLoginDto
    ) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

//    Fcm토큰 저장
    @ApiOperation(value = "Fcm토큰 저장")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized", response = JwtTokenHandler.class),
            @ApiResponse(code = 404, message = "Not Exist User", response = UserNotFoundHandler.class)
    })
    @PostMapping("/user/fcm")
    public ResponseEntity<Fcm> userFcmToken(
            @Valid @RequestBody UserFcmTokenDto userFcmTokenDto,
            HttpServletRequest httpServletRequest
    ) {
        String jwtToken = httpServletRequest.getHeader("JwtAccessToken");
        return ResponseEntity.ok(userService.saveUserFcmToken(userFcmTokenDto, jwtToken));
    }
}
