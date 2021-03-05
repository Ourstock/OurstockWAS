package com.ourstock.api.ourstock_api.service;


import com.ourstock.api.ourstock_api.dto.user.*;
import com.ourstock.api.ourstock_api.handler.user.JwtTokenException;
import com.ourstock.api.ourstock_api.handler.user.UserLoginException;
import com.ourstock.api.ourstock_api.handler.user.UserSignUpException;
import com.ourstock.api.ourstock_api.model.Fcm;
import com.ourstock.api.ourstock_api.model.UserEntity;
import com.ourstock.api.ourstock_api.repository.FcmRepository;
import com.ourstock.api.ourstock_api.repository.UserRepository;
import com.ourstock.api.ourstock_api.handler.user.UserNotFoundException;
import com.ourstock.api.ourstock_api.jwt.JwtTokenProvider;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final FcmRepository fcmRepository;
    private final JwtTokenProvider jwtTokenProvider;


    public UserService(
            UserRepository userRepository,
            FcmRepository fcmRepository,
            JwtTokenProvider jwtTokenProvider
    ) {
        this.userRepository = userRepository;
        this.fcmRepository = fcmRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

//    회원가입
    @Transactional
    public UserJwtTokenDto signup(UserSignUpDto userSignUpDto) {
        if (userRepository.findOneByCallNumber(userSignUpDto.getCallNumber()).isPresent()) {
            throw new UserSignUpException();
        }

        UserEntity userEntity = UserEntity.builder()
                .callNumber(userSignUpDto.getCallNumber())
                .telecom(userSignUpDto.getTelecom())
                .username(userSignUpDto.getUsername())
                .residentRegistrationNumberFront(userSignUpDto.getResidentRegistrationNumberFront())
                .residentRegistrationNumberBack(userSignUpDto.getResidentRegistrationNumberBack())
                .alarm(userSignUpDto.getAlarm())
                .build();

        return getUserJwtTokenDto(userEntity);
    }

    @Transactional
    public UserJwtTokenDto userRefreshToken(UserRefreshToken userRefreshToken) {
        UUID userId = userRefreshToken.getUserId();
        if (userRepository.findByUserIdAndJwtRefreshToken(userId, userRefreshToken.getJwtRefreshToken()).isPresent()) {
            UserEntity userEntity = userRepository.getByUserId(userId);
            return getUserJwtTokenDto(userEntity);
        }
        throw new UserNotFoundException();
    }

    private UserJwtTokenDto getUserJwtTokenDto(UserEntity userEntity) {
        String token = jwtTokenProvider.generateToken(userEntity);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userEntity);
        userEntity.setJwtToken(token);
        userEntity.setJwtRefreshToken(refreshToken);
        userRepository.save(userEntity);
        return UserJwtTokenDto.builder()
                .userId(userEntity.getUserId())
                .jwtToken(token)
                .jwtRefreshToken(refreshToken)
                .build();
    }


    //    로그인
    @Transactional
    public UserLoginReturnDto login(UserLoginDto userLoginDto) {
        if (userRepository.findOneByCallNumber(userLoginDto.getCallNumber()).isEmpty()) {
            throw new UserLoginException();
        }
        UserEntity userEntity = userRepository.findByCallNumber(userLoginDto.getCallNumber());

        Date now = new Date();
        userEntity.setLastConnection(now);
        userRepository.save(userEntity);

        return UserLoginReturnDto.builder()
                .userId(userEntity.getUserId())
                .jwtToken(userEntity.getJwtToken())
                .jwtRefreshToken(userEntity.getJwtRefreshToken())
                .isSuccessLogin(true)
                .build();
    }

    @Transactional
    public UserFcmTokenDto saveUserFcmToken(UserFcmTokenDto userFcmTokenDto, String jwtToken) {
        if (jwtTokenProvider.isTokenExpired(jwtToken)) {  // 토큰의 만료 검사
            if (userRepository.findByUserIdAndJwtToken(userFcmTokenDto.getUserId(), jwtToken).isPresent()) {
                Fcm fcm = Fcm.builder()
                        .fcmToken(userFcmTokenDto.getFcmToken())
                        .userId(userFcmTokenDto.getUserId())
                        .lastChecktime(new Date())
                        .build();
                fcmRepository.save(fcm);
                userFcmTokenDto.setLastCheckTime(new Date());
                return userFcmTokenDto;
            }
            throw new UserNotFoundException();
        }
        throw new JwtTokenException();
    }
//
//    @Transactional
//    public ResponseEntity<UserEntity> userDelete(long userId) {
//        userRepository.deleteById(userId);
//        return null;
//    }
}
