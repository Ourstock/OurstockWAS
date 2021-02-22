package com.ourstock.api.ourstock_api.service;


import com.ourstock.api.ourstock_api.dto.user.UserFcmTokenDto;
import com.ourstock.api.ourstock_api.dto.user.UserLoginDto;
import com.ourstock.api.ourstock_api.dto.user.UserSetAlarmDto;
import com.ourstock.api.ourstock_api.dto.user.UserSignUpDto;
import com.ourstock.api.ourstock_api.handler.user.JwtTokenHandler;
import com.ourstock.api.ourstock_api.handler.user.UserLoginHandler;
import com.ourstock.api.ourstock_api.handler.user.UserSignUpHandler;
import com.ourstock.api.ourstock_api.model.Fcm;
import com.ourstock.api.ourstock_api.model.UserEntity;
import com.ourstock.api.ourstock_api.repository.FcmRepository;
import com.ourstock.api.ourstock_api.repository.UserRepository;
import com.ourstock.api.ourstock_api.dto.user.*;
import com.ourstock.api.ourstock_api.handler.user.UserNotFoundHandler;
import com.ourstock.api.ourstock_api.jwt.JwtToken;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final FcmRepository fcmRepository;
    private final JwtToken jwtToken;

    public UserService(
            UserRepository userRepository,
            FcmRepository fcmRepository,
            JwtToken jwtToken
    ) {
        this.userRepository = userRepository;
        this.fcmRepository = fcmRepository;
        this.jwtToken = jwtToken;
    }

//    회원가입
    @Transactional
    public UserEntity signup(UserSignUpDto userSignUpDto) {
        if (userRepository.findOneByCallNumber(userSignUpDto.getCallNumber()).isPresent()) {
            throw new UserSignUpHandler();
        }
        UserEntity userEntity = UserEntity.builder()
                .callNumber(userSignUpDto.getCallNumber())
                .telecom(userSignUpDto.getTelecom())
                .username(userSignUpDto.getUsername())
                .residentRegistrationNumberFront(userSignUpDto.getResidentRegistrationNumberFront())
                .residentRegistrationNumberBack(userSignUpDto.getResidentRegistrationNumberBack())
                .build();
        return userRepository.save(userEntity);
    }

    @Transactional
    public UserEntity setAalarm(UserSetAlarmDto userSetAlarmDto) {
        if (userRepository.findById(userSetAlarmDto.getUserId()).isEmpty()) {
            throw new UserNotFoundHandler();
        }
        UserEntity userEntity = userRepository.getOne(userSetAlarmDto.getUserId());
        userEntity.setAlarm(userSetAlarmDto.getAlarm());
        userEntity.setMarketing(userSetAlarmDto.getMarketing());
        return userRepository.save(userEntity);
    }

//    로그인
    @Transactional
    public UserEntity login(UserLoginDto userLoginDto) {
        if (userRepository.findOneByCallNumber(userLoginDto.getCallNumber()).isEmpty()) {
            throw new UserLoginHandler();
        }
        UserEntity userEntity = userRepository.findByCallNumber(userLoginDto.getCallNumber());
        String token = jwtToken.createToken(userLoginDto.getCallNumber());
        Date now = new Date();
        userEntity.setJwtToken(token);
        userEntity.setLastConnection(now);
        return userRepository.save(userEntity);
    }

    @Transactional
    public boolean validJwtToken(long userId, String jwtToken) {
        return userRepository.getOne(userId).getJwtToken().equals(jwtToken);
    }

    @Transactional
    public Fcm saveUserFcmToken(UserFcmTokenDto userFcmTokenDto, String jwtToken) {
        if (userRepository.findById(userFcmTokenDto.getUserId()).isPresent()) {
            if (validJwtToken(userFcmTokenDto.getUserId(),jwtToken)) {
                Fcm fcm = Fcm.builder()
                        .fcmToken(userFcmTokenDto.getFcmToken())
                        .userId(userFcmTokenDto.getUserId())
                        .build();
                return fcmRepository.save(fcm);
            }
            throw new JwtTokenHandler();
        }
        throw new UserNotFoundHandler();
    }

    @Transactional
    public ResponseEntity<UserEntity> userDelete(long userId) {
        userRepository.deleteById(userId);
        return null;
    }
}
