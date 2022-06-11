package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class UserDelete {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotNull(message = "사용자 아이디를 입력해주세요.")
        private Long userId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long userId;

        public static Response fromEntity(Long userId){
            return Response.builder()
                    .userId(userId)
                    .build();
        }
    }
}
