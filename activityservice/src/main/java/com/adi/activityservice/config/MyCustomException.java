package com.adi.activityservice.config;

public class MyCustomException extends RuntimeException{
    public MyCustomException(String message){
        super(message);
    }
}
