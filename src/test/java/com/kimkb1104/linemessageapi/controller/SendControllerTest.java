package com.kimkb1104.linemessageapi.controller;

import com.kimkb1104.linemessageapi.controller.dto.Request;
import com.kimkb1104.linemessageapi.service.UserInfoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class SendControllerTest extends AbstractIntegrationTest {

    @Autowired
    private UserInfoService userInfoService;

    @Test
    @DisplayName("메시지 전송")
    void send() throws Exception {

        String content = objectMapper.writeValueAsString(Request.builder()
                .receiver("U782ca6f05a1076bc2d11566a6521d4eb")
                .message("메시지 테스트다")
                .build());

        mockMvc.perform(post("/send")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("success"))
                .andDo(print());
    }

    @Test
    @DisplayName("유저 목록")
    void users() throws Exception {

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().string(userInfoService.getAllUsers().toString()))
                .andDo(print());
    }
}