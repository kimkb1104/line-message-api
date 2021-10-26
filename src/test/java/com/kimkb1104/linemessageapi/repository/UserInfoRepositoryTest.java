package com.kimkb1104.linemessageapi.repository;

import com.kimkb1104.linemessageapi.entity.UserInfo;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;

class UserInfoRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private UserInfoRepository userInfoRepository;

    private List<UserInfo> userInfos = Arrays.asList(
            UserInfo.builder()
                    .id(1L)
                    .userId("U782ca6f05a1076bc2d11566a6521d4eb")
                    .isFollow(true)
                    .build(),
            UserInfo.builder()
                    .id(2L)
                    .userId("test_user2")
                    .isFollow(false)
                    .build()
    );

    @BeforeEach
    void before() {}

    @AfterEach
    void after() {}

    @Test
    @DisplayName("단일 조회")
    void read1() {
        // when
        UserInfo userInfo1 = userInfoRepository.findById(userInfos.get(0).getId()).orElseThrow(NoSuchElementException::new);
        UserInfo userInfo2 = userInfoRepository.findById(userInfos.get(1).getId()).orElseThrow(NoSuchElementException::new);

        // then
        assertThat(userInfo1.getId().equals(userInfos.get(0).getId())).isTrue();
        assertThat(userInfo1.getUserId().equals(userInfos.get(0).getUserId())).isTrue();
        assertThat(userInfo1.isFollow() == userInfos.get(0).isFollow()).isTrue();

        assertThat(userInfo2.getId().equals(userInfos.get(1).getId())).isTrue();
        assertThat(userInfo2.getUserId().equals(userInfos.get(1).getUserId())).isTrue();
        assertThat(userInfo2.isFollow() == userInfos.get(1).isFollow()).isTrue();
    }

    @Test
    @DisplayName("복수 조회")
    void read2() {
        // when
        List<UserInfo> findUserInfos = userInfoRepository.findAll();

        // then
        for (int i = 0; i < findUserInfos.size(); i++) {
            assertThat(findUserInfos.get(i).getId().equals(userInfos.get(i).getId())).isTrue();
            assertThat(findUserInfos.get(i).getUserId().equals(userInfos.get(i).getUserId())).isTrue();
            assertThat(findUserInfos.get(i).isFollow() == userInfos.get(i).isFollow()).isTrue();
        }
    }

    @Test
    @DisplayName("수정")
    void update() {
        // given
        List<UserInfo> findUserInfos = userInfoRepository.findAll();
        boolean beforeIsFollow = findUserInfos.get(0).isFollow();
        findUserInfos.get(0).unfollow();

        // when
        List<UserInfo> savedUserInfos = userInfoRepository.saveAll(findUserInfos);

        // then
        assertThat(savedUserInfos.get(0).isFollow() != beforeIsFollow).isTrue();
    }

    @Test
    @DisplayName("삭제")
    void delete() {
        // when
        userInfoRepository.deleteAll();

        // then
        assertThat(userInfoRepository.count()).isEqualTo(0L);
    }

}