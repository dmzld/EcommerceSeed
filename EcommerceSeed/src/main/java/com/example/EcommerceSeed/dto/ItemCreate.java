package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.User;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ItemCreate {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotBlank(message = "상품 이름을 입력해주세요.")
        private String itemName;
        @NotBlank(message = "상품 타입을 입력해주세요.")
        private String itemType;
        @NotNull(message = "상품 가격을 입력해주세요.")
        private Long itemPrice;
        @NotNull(message = "상품 전시 시작일을 입력해주세요.")
        private Date itemDisplayStartDate;
        @NotNull(message = "상품 전시 종료일을 입력해주세요.")
        private Date itemDisplayEndDate;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String itemName;
        private String itemType;
        private Long itemPrice;
        private Date itemDisplayStartDate;
        private Date itemDisplayEndDate;

        public static Response fromEntity(Item item){
            return Response.builder()
                    .itemName(item.getItemName())
                    .itemType(item.getItemType())
                    .itemPrice(item.getItemPrice())
                    .itemDisplayStartDate(item.getItemDisplayStartDate())
                    .itemDisplayEndDate(item.getItemDisplayEndDate())
                    .build();
        }
    }
}
