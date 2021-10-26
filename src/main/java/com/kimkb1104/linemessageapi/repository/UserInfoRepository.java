package com.kimkb1104.linemessageapi.repository;

import com.kimkb1104.linemessageapi.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

    Optional<UserInfo> findByUserId(String userId);
}
