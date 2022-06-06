package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.Item;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class ItemSelectPromotionList {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{

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
        private String itemName;
        private String itemType;
        private Long itemPrice;
        private Date itemDisplayStartDate;
        private Date itemDisplayEndDate;

        public static Response fromEntity(Item item){
            return Response.builder()
                    .itemName(item.getItemName())
                    .itemType(item.getItemType())
                    .itemPrice(item.getItemId())
                    .itemDisplayStartDate(item.getItemDisplayStartDate())
                    .itemDisplayEndDate(item.getItemDisplayEndDate())
                    .build();
        }
    }
}
