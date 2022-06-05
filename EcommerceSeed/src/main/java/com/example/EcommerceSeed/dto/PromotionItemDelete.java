package com.example.EcommerceSeed.dto;

import lombok.*;

public class PromotionItemDelete {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @ToString
    public static class Request{
        private Long promotionId;
        private Long itemId;

        public Request toEntity(){
            return Request.builder()
                    .itemId(itemId)
                    .promotionId(promotionId)
                    .build();
        }
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
