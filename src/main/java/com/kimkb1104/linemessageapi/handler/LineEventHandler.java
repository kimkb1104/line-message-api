package com.kimkb1104.linemessageapi.handler;

import com.kimkb1104.linemessageapi.service.SendService;
import com.kimkb1104.linemessageapi.service.UserInfoService;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.RequiredArgsConstructor;

/**
 * CALLBACK HANDLER(LINE MESSAGE SERVER -> MY SERVER)
 */
@RequiredArgsConstructor
@LineMessageHandler
public class LineEventHandler {

    private final SendService sendService;
    private final UserInfoService userInfoService;

    @EventMapping
    public void followEvent(FollowEvent followEvent) {

        sendService.follow(followEvent.getSource().getUserId(), "Thanks for following");
    }

    @EventMapping
    public void unfollowEvent(UnfollowEvent unfollowEvent) {

        userInfoService.unfollow(unfollowEvent.getSource().getUserId());
    }

}
