package com.example.EcommerceSeed.config;

import com.example.EcommerceSeed.dto.response.BaseResponse;
import com.example.EcommerceSeed.exception.InvalidRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TotalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity<BaseResponse> invalidRequestInputException(InvalidRequestException e){
        System.out.println(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(e.getMessage()));
    }
}
