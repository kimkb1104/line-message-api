package com.kimkb1104.linemessageapi.controller;

import com.kimkb1104.linemessageapi.controller.dto.Request;
import com.kimkb1104.linemessageapi.service.SendService;
import com.kimkb1104.linemessageapi.service.UserInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 테스트용 컨트롤러
 */
@RequiredArgsConstructor
@RestController
public class SendController {

    private final SendService sendService;
    private final UserInfoService userInfoService;

    @PostMapping("/send")
    public String sendAll(@RequestBody Request request) {

        if (request.getReceiver() == null)
            sendService.sendAll(request.getMessage());
        else
            sendService.send(request.getReceiver(), request.getMessage());

        return "success";
    }

    @GetMapping("/users")
    public String users() {

        return userInfoService.getAllUsers().toString();
    }
}
