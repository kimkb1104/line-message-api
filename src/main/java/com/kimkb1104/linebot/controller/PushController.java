package com.kimkb1104.linebot.controller;

import com.kimkb1104.linebot.dto.Request;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PushController {

    private final LineMessagingClient lineMessagingClient;

    @PostMapping("/push")
    public ResponseEntity push(@RequestBody Request request) {
        lineMessagingClient.pushMessage(
                new PushMessage(
                        request.getUserId()
                        , new TextMessage(request.getMessage())
                )
        );

        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
