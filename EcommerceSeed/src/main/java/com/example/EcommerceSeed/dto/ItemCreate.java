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
        @NotBlank
        private String itemName;
        @NotBlank
        private String itemType;
        @NotNull
        private Long itemPrice;
        @NotNull
        private Date itemDisplayStartDate;
        @NotNull
        private Date itemDisplayEndDate;

        public Request toEntity(){
            return Request.builder()
                    .itemName(itemName)
                    .itemType(itemType)
                    .itemPrice(itemPrice)
                    .itemDisplayStartDate(itemDisplayStartDate)
                    .itemDisplayEndDate(itemDisplayEndDate)
                    .build();
        }
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
