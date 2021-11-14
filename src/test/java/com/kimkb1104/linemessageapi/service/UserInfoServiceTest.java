package com.kimkb1104.linemessageapi.service;

import com.kimkb1104.linemessageapi.entity.UserInfo;
import com.kimkb1104.linemessageapi.repository.UserInfoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class UserInfoServiceTest {

    @InjectMocks
    private UserInfoService userInfoService;

    @Mock
    private UserInfoRepository userInfoRepository;

    @Test
    @DisplayName("유저 생성")
    void createUser() {

        // given
        String userId = "test_user3";
        UserInfo userInfo = UserInfo.builder()
                .userId(userId)
                .isFollow(true)
                .build();
        Mockito.when(userInfoRepository.save(any()))
                .thenReturn(userInfo);

        // when
        UserInfo findUserInfo = userInfoService.createUser(userId);

        // then
        assertThat(userInfo.equals(findUserInfo)).isTrue();
    }

    @Test
    @DisplayName("유저 가져오기")
    void getUser() {

        // given
        String userId = "test_user2";
        UserInfo userInfo = UserInfo.builder()
                .userId(userId)
                .isFollow(true)
                .build();
        Mockito.when(userInfoRepository.findByUserId(any()))
                .thenReturn(Optional.of(userInfo));

        // when
        UserInfo findUserInfo = userInfoService.getUser(userId);

        // then
        assertThat(userInfo.equals(findUserInfo)).isTrue();
    }

    @Test
    @DisplayName("언팔로우")
    void unfollow() {

        // given
        String userId = "test_user3";
        UserInfo userInfo = UserInfo.builder()
                .userId(userId)
                .isFollow(true)
                .build();

        UserInfo saveUserInfo = UserInfo.builder()
                .userId(userId)
                .isFollow(false)
                .build();

        Mockito.when(userInfoRepository.findByUserId(userId))
                .thenReturn(Optional.ofNullable(userInfo));
        Mockito.when(userInfoRepository.save(any()))
                .thenReturn(saveUserInfo);

        // when
        UserInfo findUserInfo = userInfoService.unfollow(userId);

        // then
        assertThat(saveUserInfo.equals(findUserInfo)).isTrue();
    }
}