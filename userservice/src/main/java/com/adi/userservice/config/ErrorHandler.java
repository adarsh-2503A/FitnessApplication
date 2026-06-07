package com.adi.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class ErrorHandler {

    @Autowired
    private Environment environment;

    @ExceptionHandler
    public ResponseEntity<ErrorResponseMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage();
        errorResponseMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponseMessage.setTimeStamp(LocalDateTime.now());
        errorResponseMessage.setErrorMessage(exception.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining(",")));
        return new ResponseEntity<>(errorResponseMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseMessage> handleMyCustomException(MyCustomException exception){
        ErrorResponseMessage errorResponseMessage = new ErrorResponseMessage();
        errorResponseMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorResponseMessage.setTimeStamp(LocalDateTime.now());
        errorResponseMessage.setErrorMessage(environment.getProperty(exception.getMessage()));
        return new ResponseEntity<>(errorResponseMessage, HttpStatus.BAD_REQUEST);
    }
}
