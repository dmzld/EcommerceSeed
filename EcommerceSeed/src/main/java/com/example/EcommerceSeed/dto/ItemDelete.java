package com.example.EcommerceSeed.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ItemDelete {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotNull(message = "상품 아이디를 입력해주세요.")
        private Long itemId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long itemId;

        public static Response fromEntity(Long itemId){
            return Response.builder()
                    .itemId(itemId)
                    .build();
        }
    }
}
