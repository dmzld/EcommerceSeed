package com.example.EcommerceSeed.exception;

import com.example.EcommerceSeed.util.type.ErrorCodes;
import lombok.Getter;

@Getter
public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(){ super("잘못된 request입니다."); }
    public InvalidRequestException(String message){
        super(message);
    }
}
