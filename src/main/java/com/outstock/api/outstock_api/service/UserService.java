package com.outstock.api.outstock_api.service;


import com.outstock.api.outstock_api.dto.user.UserFcmTokenDto;
import com.outstock.api.outstock_api.dto.user.UserLoginDto;
import com.outstock.api.outstock_api.dto.user.UserSignUpDto;
import com.outstock.api.outstock_api.handler.user.JwtTokenHandler;
import com.outstock.api.outstock_api.handler.user.UserLoginHandler;
import com.outstock.api.outstock_api.handler.user.UserNotFoundHandler;
import com.outstock.api.outstock_api.handler.user.UserSignUpHandler;
import com.outstock.api.outstock_api.jwt.JwtToken;
import com.outstock.api.outstock_api.model.Fcm;
import com.outstock.api.outstock_api.model.UserEntity;
import com.outstock.api.outstock_api.repository.FcmRepository;
import com.outstock.api.outstock_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


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
                .alarm(userSignUpDto.getAlarm())
                .marketing(userSignUpDto.getMarketing())
                .build();
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
        userEntity.setJwtToken(token);
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
}
