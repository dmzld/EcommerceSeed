package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.Item;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
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
        @NotNull(message = "사용자 아이디를 입력해주세요.")
        private Long userId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private Long itemId;
        private String itemName;
        private String itemType;
        private Long itemPrice;
        private Date itemDisplayStartDate;
        private Date itemDisplayEndDate;

        public static Response fromEntity(Item item){
            return Response.builder()
                        .itemId(item.getItemId())
                        .itemName(item.getItemName())
                        .itemType(item.getItemType())
                        .itemPrice(item.getItemPrice())
                        .itemDisplayStartDate(item.getItemDisplayStartDate())
                        .itemDisplayEndDate(item.getItemDisplayEndDate())
                        .build();
        }
    }
}
