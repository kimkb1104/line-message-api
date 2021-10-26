package com.kimkb1104.linemessageapi.service;

import com.kimkb1104.linemessageapi.entity.UserInfo;
import com.kimkb1104.linemessageapi.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserInfoService {
    private final UserInfoRepository userInfoRepository;

    public List<UserInfo> getAllUsers() {

        return userInfoRepository.findAll();
    }

    public UserInfo getUser(String userId) {

        return userInfoRepository.findByUserId(userId)
                .orElseThrow(IllegalArgumentException::new);
    }

    public UserInfo createUser(String userId) {

        return userInfoRepository.save(
                userInfoRepository.findByUserId(userId)
                        .orElse(UserInfo.builder()
                                .userId(userId)
                                .isFollow(true)
                                .build()));
    }

    public UserInfo unfollow(String userId) {

        return userInfoRepository.save(
                userInfoRepository.findByUserId(userId)
                        .orElseThrow(IllegalArgumentException::new)
                        .unfollow());
    }
}
