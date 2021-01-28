package com.outstock.api.outstock_api.repository;

import com.outstock.api.outstock_api.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

    Optional<UserEntity> findOneByCallNumber(String callNumber);
    UserEntity findByCallNumber(String callNumber);
}
