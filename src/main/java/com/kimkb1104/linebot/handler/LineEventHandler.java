package com.kimkb1104.linebot.handler;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.ReplyMessage;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@LineMessageHandler
public class LineEventHandler {

    private final LineMessagingClient lineMessagingClient;

    @EventMapping
    public void followEvent(FollowEvent followEvent) {
        lineMessagingClient.replyMessage(
                new ReplyMessage(
                        followEvent.getReplyToken()
                        , new TextMessage("Thanks for following")
                )
        );
    }

    @EventMapping
    public void unfollowEvent(UnfollowEvent unfollowEvent) {
    }

}
