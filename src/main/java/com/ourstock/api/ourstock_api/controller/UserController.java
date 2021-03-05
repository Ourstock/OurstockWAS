package com.ourstock.api.ourstock_api.controller;


import com.ourstock.api.ourstock_api.dto.user.*;
import com.ourstock.api.ourstock_api.handler.user.JwtTokenException;
import com.ourstock.api.ourstock_api.jwt.JwtTokenProvider;
import com.ourstock.api.ourstock_api.model.Fcm;
import com.ourstock.api.ourstock_api.handler.user.UserNotFoundException;
import com.ourstock.api.ourstock_api.handler.user.UserSignUpException;
import com.ourstock.api.ourstock_api.model.UserEntity;
import com.ourstock.api.ourstock_api.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Map;

@RestController
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    회원가입
    @ApiOperation(value = "회원가입")
    @ApiResponses(value = {
            @ApiResponse(code = 409, message = "Exist User", response = UserSignUpException.class)
    })
    @PostMapping("/register")
    public UserJwtTokenDto registerUser(
            @Valid @RequestBody UserSignUpDto userSignUpDto
    ) {
        return userService.signup(userSignUpDto);
    }

//    Refresh Token
    @ApiOperation(value = "리프레쉬 토큰")
    @PostMapping("/refresh")
    public UserJwtTokenDto refreshToken(
            @Valid @RequestBody UserRefreshToken userRefreshToken
    ) {
        return userService.userRefreshToken(userRefreshToken);
    }

//    로그인
    @ApiOperation(value = "로그인")
    @ApiResponses(value = {
            @ApiResponse(code = 404, message = "Not Exist User", response = UserNotFoundException.class)
    })
    @PostMapping("/login")
    public ResponseEntity<UserLoginReturnDto> loginUser(
            @Valid @RequestBody UserLoginDto userLoginDto
    ) {
        return ResponseEntity.ok(userService.login(userLoginDto));
    }

//    Fcm토큰 저장
    @ApiOperation(value = "Fcm토큰 저장")
    @ApiResponses(value = {
            @ApiResponse(code = 401, message = "Unauthorized", response = JwtTokenException.class),
            @ApiResponse(code = 404, message = "Not Exist User", response = UserNotFoundException.class)
    })
    @PostMapping("/user/fcm")
    public UserFcmTokenDto saveFcmToken(@Valid @RequestBody UserFcmTokenDto userFcmTokenDto, HttpServletRequest httpServletRequest) {
        return userService.saveUserFcmToken(userFcmTokenDto, httpServletRequest.getHeader("Authorization"));
    }
//
//    @PostMapping("/user/delete/{userId}")
//    public ResponseEntity<UserEntity> deleteUser(
//            @PathVariable long userId
//    ) {
//        return userService.userDelete(userId);
//    }


}
