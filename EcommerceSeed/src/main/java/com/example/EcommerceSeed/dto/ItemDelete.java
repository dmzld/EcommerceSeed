package com.example.EcommerceSeed.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;

public class ItemDelete {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotBlank(message = "itemId을 확인해주세요.")
        private Long itemId;

        public Request toEntity(){
            return Request.builder()
                    .itemId(itemId)
                    .build();
        }
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
