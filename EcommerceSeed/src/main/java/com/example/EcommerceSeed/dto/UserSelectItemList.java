package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.Item;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

public class UserSelectItemList {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
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
        private List<Item> itemList;

        public static Response fromEntity(List<Item> itemList){
            return Response.builder()
                    .itemList(itemList)
                    .build();
        }
    }
}
