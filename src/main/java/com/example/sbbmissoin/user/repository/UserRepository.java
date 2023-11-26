package com.example.sbbmissoin.user.repository;

import com.example.sbbmissoin.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity,Long> {
}
