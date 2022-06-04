package com.example.EcommerceSeed.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

public class PromotionDelete {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotBlank(message = "itemId을 확인해주세요.")
        private Long promotionId;

        public Request toEntity(){
            return Request.builder()
                    .promotionId(promotionId)
                    .build();
        }
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