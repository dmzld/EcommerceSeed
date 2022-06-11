package com.example.EcommerceSeed.service;

import com.example.EcommerceSeed.dto.PromotionCreate;
import com.example.EcommerceSeed.dto.PromotionDelete;
import com.example.EcommerceSeed.dto.PromotionItemCreate;
import com.example.EcommerceSeed.dto.PromotionItemDelete;
import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
import com.example.EcommerceSeed.entity.PromotionItem;
import com.example.EcommerceSeed.exception.InvalidRequestException;
import com.example.EcommerceSeed.repository.ItemRepository;
import com.example.EcommerceSeed.repository.PromotionItemRepository;
import com.example.EcommerceSeed.repository.PromotionRepository;
import com.example.EcommerceSeed.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class PromotionService {
    private final PromotionRepository promotionRepository;
    private final ItemRepository itemRepository;
    private final PromotionItemRepository promotionItemRepository;

    /*
     * 프로모션 생성
     * 1. 할인정액, 할인정률 입력값 validation
     * 2. 프로모션 생성
     * 3. return 생성된 프로모션
     */
    @Transactional
    public PromotionCreate.Response createPromotion(PromotionCreate.Request request){
        if(request.getDiscountAmount() == null && request.getDiscountRate() == null){
            throw new InvalidRequestException(MessageUtils.NO_PROMOTION_DISCOUNT_VALUE);
        }

       return PromotionCreate.Response.fromEntity(promotionRepository.save(
               Promotion.builder()
                       .promotionNm(request.getPromotionNm())
                       .discountAmount(request.getDiscountAmount())
                       .discountRate(request.getDiscountRate())
                       .promotionStartDate(request.getPromotionStartDate())
                       .promotionEndDate(request.getPromotionEndDate())
                       .build()
       ));
    }

    /*
     * 프로모션 삭제
     * 1. 프로모션 조회
     * 2. 프로모션 적용된 상품 조회
     * 3. 프로모션 삭제
     * 4. return 삭제된 프로모션 ID
     */
    @Transactional
    public PromotionDelete.Response deletePromotion(PromotionDelete.Request request){
        promotionRepository.findById(request.getPromotionId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_PROMOTION_ID));

        if(promotionItemRepository.findByPromotionId(request.getPromotionId()).isPresent()){
            throw new InvalidRequestException(MessageUtils.PROMOTION_ITEM_EXIST);
        }
        promotionRepository.deleteById(request.getPromotionId());

        return PromotionDelete.Response.fromEntity(request.getPromotionId());
    }

    /*
     * 프로모션 상품 적용 내역 생성
     * 1. 요청된 프로모션 상품 기적용 여부 조회
     * 2. 프로모션, 상품 조회
     * 3. 프로모션 상품 적용 내역 생성
     * 4. return 생성된 프로모션 상품 적용 내역의 프로모션ID, 상품ID
     */
    @Transactional
    public PromotionItemCreate.Response createPromotionItem(PromotionItemCreate.Request request){
        if(promotionItemRepository.existsByPromotionIdAndItemId(request.getPromotionId(), request.getItemId()).isPresent()){
            throw new InvalidRequestException(MessageUtils.PROMOTION_ITEM_ALREADY_EXIST);
        }

        Promotion promotion = promotionRepository.findById(request.getPromotionId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_PROMOTION_ID));
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_PROMOTION_ID));

        PromotionItem promotionItem = promotionItemRepository.save(new PromotionItem(promotion, item));
        return PromotionItemCreate.Response.fromEntity(promotionItem.getPromotion().getPromotionId(), promotionItem.getItem().getItemId());
    }

    /*
     * 프로모션 상품 적용 내역 삭제
     * 1. 프로모선 상품 적용 내역 조회 및 삭제
     * 2. return 삭제된 프로모션 상품 적용 내역의 프로모션ID, 상품ID
     */
    @Transactional
    public PromotionItemDelete.Response deletePromotionItem(PromotionItemDelete.Request request){
        promotionItemRepository.delete(promotionItemRepository.existsByPromotionIdAndItemId(request.getPromotionId(), request.getItemId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_PROMOTION_ITEM)));

        return PromotionItemDelete.Response.fromEntity(request.getPromotionId(), request.getItemId());
    }
}
