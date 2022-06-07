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

    @Transactional
    public PromotionCreate.Response createPromotion(PromotionCreate.Request request){
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

    @Transactional
    public PromotionItemDelete.Response deletePromotionItem(PromotionItemDelete.Request request){
        promotionItemRepository.delete(promotionItemRepository.existsByPromotionIdAndItemId(request.getPromotionId(), request.getItemId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_PROMOTION_ITEM)));

        return PromotionItemDelete.Response.fromEntity(request.getPromotionId(), request.getItemId());
    }
}
