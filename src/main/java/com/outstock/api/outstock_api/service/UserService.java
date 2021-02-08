package com.outstock.api.outstock_api.service;


import com.outstock.api.outstock_api.dto.user.UserLoginDto;
import com.outstock.api.outstock_api.dto.user.UserSignUpDto;
import com.outstock.api.outstock_api.handler.user.UserLoginHandler;
import com.outstock.api.outstock_api.handler.user.UserSignUpHandler;
import com.outstock.api.outstock_api.jwt.JwtToken;
import com.outstock.api.outstock_api.model.UserEntity;
import com.outstock.api.outstock_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class UserService {

    private final UserRepository userRepository;
    private final JwtToken jwtToken;

    public UserService(
            UserRepository userRepository,
            JwtToken jwtToken
    ) {
        this.userRepository = userRepository;
        this.jwtToken = jwtToken;
    }

//    회원가입
    @Transactional
    public UserEntity signup(UserSignUpDto userSignUpDto) {
        if (userRepository.findOneByCallNumber(userSignUpDto.getCallNumber()).isPresent()) {
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
        throw new UserSignUpHandler();
    }
//    로그인
    @Transactional
    public UserEntity login(UserLoginDto userLoginDto) {
        if (userRepository.findOneByCallNumber(userLoginDto.getCallNumber()).orElse(null) == null) {
            throw new UserLoginHandler();
        }

        UserEntity userEntity = userRepository.findByCallNumber(userLoginDto.getCallNumber());
        String token = jwtToken.createToken(userLoginDto.getCallNumber());
        userEntity.setJwtToken(token);
        return userRepository.save(userEntity);
    }

}
