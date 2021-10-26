package com.kimkb1104.linemessageapi.controller.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Request {

    public String receiver;
    public String message;
}
