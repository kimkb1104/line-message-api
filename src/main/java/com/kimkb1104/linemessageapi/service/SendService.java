package com.kimkb1104.linemessageapi.service;

import com.kimkb1104.linemessageapi.entity.UserInfo;
import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.message.TextMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class SendService {

    private final LineMessagingClient lineMessagingClient;
    private final UserInfoService userInfoService;

    public void follow(String receiver, String message) {

        send(userInfoService.createUser(receiver).getUserId(), message);
    }

    public void sendAll(String message) {

        send(userInfoService.getAllUsers(), message);
    }

    public void send(List<UserInfo> userInfos, String message) {

        for (UserInfo userInfo : userInfos)
            send(userInfo.getUserId(), message);
    }

    public void send(String receiver, String message) {

        lineMessagingClient.pushMessage(
                new PushMessage(receiver , new TextMessage(message))
        );
    }
}
