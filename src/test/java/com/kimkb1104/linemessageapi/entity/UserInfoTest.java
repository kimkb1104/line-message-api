package com.kimkb1104.linemessageapi.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserInfoTest {

    private UserInfo userInfo;

    private final boolean beforeIsFollow = true;

    @BeforeEach
    void before() {

        userInfo = UserInfo.builder()
                .id(1L)
                .userId("testUser1")
                .isFollow(beforeIsFollow)
                .build();
    }

    @Test
    @DisplayName("언팔로우")
    void unfollow() {

        // when
        userInfo = userInfo.unfollow();

        // then
        assertThat(userInfo.isFollow() != beforeIsFollow).isTrue();
    }

}