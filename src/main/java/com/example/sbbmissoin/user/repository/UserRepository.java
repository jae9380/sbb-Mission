package com.example.sbbmissoin.user.repository;

import com.example.sbbmissoin.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByusername(String username);
}
