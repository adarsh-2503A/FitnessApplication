package com.adi.userservice.config;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ErrorResponseMessage {
    private String errorMessage;
    private Integer statusCode;
    private LocalDateTime timeStamp;
}
