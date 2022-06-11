package com.example.EcommerceSeed.config;

import com.example.EcommerceSeed.dto.response.BaseResponse;
import com.example.EcommerceSeed.exception.InvalidRequestException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class TotalExceptionHandler {

    @ExceptionHandler(InvalidRequestException.class)
    protected ResponseEntity invalidRequestInputException(InvalidRequestException e){
        System.out.println(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new BaseResponse(e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        System.out.println(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseResponse(e.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()).toString()));
    }

    @ExceptionHandler(BindException.class)
    protected ResponseEntity BindException(BindException e){
        System.out.println(e.getMessage());
        e.printStackTrace();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new BaseResponse(e.getAllErrors()
                        .stream()
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .collect(Collectors.toList()).toString()));
    }
}
