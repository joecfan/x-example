package com.example.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author: zhuhui bao
 * @date: 17:09 2020/1/14
 **/
@ControllerAdvice
public class ExceptionExHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageInfo> runtimeExHandler(RuntimeException ex) {

        HttpHeaders headers = new HttpHeaders();

        MessageInfo messageInfo = new MessageInfo();
        messageInfo.setMessage(ex.getMessage());
        ResponseEntity<MessageInfo> responseEntity = new ResponseEntity(messageInfo, headers, HttpStatus.INTERNAL_SERVER_ERROR);

        return responseEntity;
    }
}
