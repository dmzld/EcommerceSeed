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
        @NotBlank
        private String userName;
        @NotBlank
        private String userType;
        @NotBlank
        private String userStat;

        public Request toEntity(){
            return Request.builder()
                    .userName(userName)
                    .userType(userType)
                    .userStat(userStat)
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
