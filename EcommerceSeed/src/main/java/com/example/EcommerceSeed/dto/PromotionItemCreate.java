package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
import com.example.EcommerceSeed.entity.PromotionItem;
import lombok.*;

import java.util.List;

public class PromotionItemCreate {

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
