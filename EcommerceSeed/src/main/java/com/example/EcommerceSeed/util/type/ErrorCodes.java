package com.example.EcommerceSeed.util.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {
   CANNOT_FIND_USER("해당되는 유저를 찾을 수 없습니다.");

    private final String message;
}
