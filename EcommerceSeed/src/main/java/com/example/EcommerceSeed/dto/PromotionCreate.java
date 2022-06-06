package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;

public class PromotionCreate {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotBlank(message = "promotionName을 확인해주세요.")
        private String promotionNm;
        private Long discountAmount;
        private Double discountRate;
        private Date promotionStartDate;
        private Date promotionEndDate;

        public Request toEntity(){
            return Request.builder()
                    .promotionNm(promotionNm)
                    .discountAmount(discountAmount)
                    .discountRate(discountRate)
                    .promotionStartDate(promotionStartDate)
                    .promotionEndDate(promotionStartDate)
                    .build();
        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response{
        private String promotionNm;
        private Long discountAmount;
        private Double discountRate;
        private Date promotionStartDate;
        private Date promotionEndDate;

        public static Response fromEntity(Promotion promotion){
            return Response.builder()
                    .promotionNm(promotion.getPromotionNm())
                    .discountAmount(promotion.getDiscountAmount())
                    .discountRate(promotion.getDiscountRate())
                    .promotionStartDate(promotion.getPromotionStartDate())
                    .promotionEndDate(promotion.getPromotionEndDate())
                    .build();
        }
    }
}
