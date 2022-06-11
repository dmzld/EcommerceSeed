package com.example.EcommerceSeed.dto;

import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class PromotionCreate {

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    @ToString
    public static class Request{
        @NotBlank(message = "프로모션 이름을 입력해주세요.")
        private String promotionNm;
        private Long discountAmount;
        private Double discountRate;
        @NotNull(message = "프로모션 시작일을 입력해주세요.")
        private Date promotionStartDate;
        @NotNull(message = "프로모션 종료일을 입력해주세요.")
        private Date promotionEndDate;
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
