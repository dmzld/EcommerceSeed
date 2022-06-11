package com.example.EcommerceSeed.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PromotionItemDelete {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotNull(message = "프로모션 아이디를 입력해주세요.")
        private Long promotionId;
        @NotNull(message = "아이템 아이디를 입력해주세요.")
        private Long itemId;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long promotionId;
        private Long itemId;

        public static Response fromEntity(Long promotionId, Long itemId){
            return Response.builder()
                    .promotionId(promotionId)
                    .itemId(itemId)
                    .build();
        }
    }
}
