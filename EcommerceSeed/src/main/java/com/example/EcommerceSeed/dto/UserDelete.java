package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;

public class UserDelete {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotBlank(message = "userId을 확인해주세요.")
        private Long userId;

        public Request toEntity(){
            return Request.builder()
                    .userId(userId)
                    .build();
        }
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
