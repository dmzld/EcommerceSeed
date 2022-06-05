package com.example.EcommerceSeed.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.example.EcommerceSeed.dto.PromotionCreate;
import com.example.EcommerceSeed.dto.PromotionDelete;
import com.example.EcommerceSeed.dto.PromotionItemCreate;
import com.example.EcommerceSeed.dto.PromotionItemDelete;
import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
import com.example.EcommerceSeed.entity.PromotionItem;
import com.example.EcommerceSeed.exception.UserException;
import com.example.EcommerceSeed.repository.ItemRepository;
import com.example.EcommerceSeed.repository.PromotionItemRepository;
import com.example.EcommerceSeed.repository.PromotionRepository;
import com.example.EcommerceSeed.type.UserErrorCode;
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

    @Transactional
    public PromotionItemCreate.Response createPromotionItem(PromotionItemCreate.Request request){
        PromotionItem promotionItem = promotionItemRepository.existsByPromotionIdAndItemId(request.getPromotionId(), request.getItemId());

        if(promotionItem != null){
            return PromotionItemCreate.Response.fromEntity(promotionItem.getPromotion().getPromotionId(), promotionItem.getItem().getItemId());
        }
        else{
            Promotion promotion = promotionRepository.findById(request.getPromotionId()).orElse(null);
            Item item = itemRepository.findById(request.getItemId()).orElse(null);
            promotionItem = promotionItemRepository.save(new PromotionItem(promotion, item));
            return PromotionItemCreate.Response.fromEntity(promotionItem.getPromotion().getPromotionId(), promotionItem.getItem().getItemId());
        }
    }

    @Transactional
    public PromotionItemDelete.Response deletePromotionItem(PromotionItemDelete.Request request){
        PromotionItem promotionItem = promotionItemRepository.existsByPromotionIdAndItemId(request.getPromotionId(), request.getItemId());

        if(promotionItem != null){
            promotionItemRepository.deletePromotionItemByPromotionIdAndItemId(request.getPromotionId(), request.getItemId());
            return PromotionItemDelete.Response.fromEntity(request.getPromotionId(), request.getItemId());
        }
        else{
            return PromotionItemDelete.Response.fromEntity(request.getPromotionId(), request.getItemId());
        }


    }
}
