package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
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
        private Long itemId;
        private String itemName;
        private String itemType;
        private Long itemPrice;
        private Date itemDisplayStartDate;
        private Date itemDisplayEndDate;

        private Long promotionId;
        private String promotionNm;
        private Long discountAmount;
        private Double discountRate;
        private Date promotionStartDate;
        private Date promotionEndDate;

        private Double itemPriceAppliedPromotion;

        public static Response fromEntity(Item item, Promotion promotion, Double itemPriceAppliedPromotion){
            return Response.builder()
                    .itemId(item.getItemId())
                    .itemName(item.getItemName())
                    .itemType(item.getItemType())
                    .itemPrice(item.getItemPrice())
                    .itemDisplayStartDate(item.getItemDisplayStartDate())
                    .itemDisplayEndDate(item.getItemDisplayEndDate())

                    .promotionId(promotion.getPromotionId())
                    .promotionNm(promotion.getPromotionNm())
                    .discountAmount(promotion.getDiscountAmount())
                    .discountRate(promotion.getDiscountRate())
                    .promotionStartDate(promotion.getPromotionStartDate())
                    .promotionEndDate(promotion.getPromotionEndDate())
                    .itemPriceAppliedPromotion(itemPriceAppliedPromotion)
                    .build();
        }
    }
}
