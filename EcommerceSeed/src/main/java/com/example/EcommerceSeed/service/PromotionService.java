package com.example.EcommerceSeed.service;

import com.example.EcommerceSeed.dto.PromotionCreate;
import com.example.EcommerceSeed.dto.PromotionDelete;
import com.example.EcommerceSeed.entity.Promotion;
import com.example.EcommerceSeed.exception.UserException;
import com.example.EcommerceSeed.repository.PromotionRepository;
import com.example.EcommerceSeed.type.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;

    @Transactional
    public PromotionCreate.Response createPromotion(PromotionCreate.Request request){
        // validate

        Promotion promotion = Promotion.builder()
                .promotionNm(request.getPromotionNm())
                .discountAmount(request.getDiscountAmount())
                .discountRate(request.getDiscountRate())
                .promotionStartDate(request.getPromotionStartDate())
                .promotionEndDate(request.getPromotionEndDate())
                .build();

        return PromotionCreate.Response.fromEntity(promotionRepository.save(promotion));
    }

    @Transactional
    public PromotionDelete.Response deletePromotion(PromotionDelete.Request request){
        promotionRepository.delete(
                promotionRepository.findById(request.getPromotionId()).orElseThrow(() -> new UserException(UserErrorCode.CANNOT_FIND_USER))
        );
        return PromotionDelete.Response.fromEntity(request.getPromotionId());
    }

}
