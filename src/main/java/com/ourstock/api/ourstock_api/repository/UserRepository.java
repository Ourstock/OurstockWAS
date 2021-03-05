package com.ourstock.api.ourstock_api.repository;

import com.ourstock.api.ourstock_api.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {

    Optional<UserEntity> findOneByCallNumber(String callNumber);
    UserEntity findByCallNumber(String callNumber);
    
    Optional<UserEntity> findByUserIdAndJwtRefreshToken(UUID userId, String jwtRefreshToken);

    UserEntity getByUserId(UUID userId);

    Optional<UserEntity> findByUserIdAndJwtToken(UUID userId, String jwtToken);
}
