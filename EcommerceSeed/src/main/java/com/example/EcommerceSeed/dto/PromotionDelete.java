package com.example.EcommerceSeed.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PromotionDelete {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotNull(message = "프로모션 아이디를 입력해주세요.")
        private Long promotionId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long promotionId;

        public static Response fromEntity(Long promotionId){
            return Response.builder()
                    .promotionId(promotionId)
                    .build();
        }
    }
}
