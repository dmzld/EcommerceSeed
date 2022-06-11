package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.User;
import lombok.*;

import javax.validation.constraints.*;

public class UserCreate {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotBlank(message = "사용자 이름을 입력해주세요.")
        private String userName;
        @NotBlank(message = "사용자 타입을 입력해주세요.")
        private String userType;
        @NotBlank(message = "사용자 상태를 입력해주세요.")
        private String userStat;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long userId;
        private String userName;
        private String userType;
        private String userStat;

        public static Response fromEntity(User user){
            return Response.builder()
                    .userId(user.getUserId())
                    .userName(user.getUserName())
                    .userType(user.getUserType())
                    .userStat(user.getUserStat())
                    .build();
        }
    }
}
