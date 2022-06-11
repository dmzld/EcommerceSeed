package com.example.EcommerceSeed.service;

import com.example.EcommerceSeed.dto.ItemCreate;
import com.example.EcommerceSeed.dto.ItemDelete;
import com.example.EcommerceSeed.dto.ItemSelectPromotionList;
import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
import com.example.EcommerceSeed.exception.InvalidRequestException;
import com.example.EcommerceSeed.repository.ItemRepository;
import com.example.EcommerceSeed.repository.PromotionItemRepository;
import com.example.EcommerceSeed.repository.PromotionRepository;
import com.example.EcommerceSeed.util.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final PromotionItemRepository promotionItemRepository;
    private final PromotionRepository promotionRepository;

    /*
     * 상품생성
     * 1. 상품 생성
     * 2. return 생성된 상품
     */
    @Transactional
    public ItemCreate.Response createItem(ItemCreate.Request request){
        return ItemCreate.Response.fromEntity(itemRepository.save(
                Item.builder()
                        .itemName(request.getItemName())
                        .itemType(request.getItemType())
                        .itemPrice(request.getItemPrice())
                        .itemDisplayStartDate(request.getItemDisplayStartDate())
                        .itemDisplayEndDate(request.getItemDisplayEndDate())
                        .build()
        ));
    }

    /*
     * 상품삭제
     * 1. 상품 조회
     * 2. 상품에등록된프로모션조회
     * 3. 상품삭제
     * 4. return 삭제된 상품 ID
     */
    @Transactional
    public ItemDelete.Response deleteItem(ItemDelete.Request request){
        itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_ITEM_ID));

        if(promotionItemRepository.findByItemId(request.getItemId()).isPresent()){
            throw new InvalidRequestException(MessageUtils.PROMOTION_ITEM_EXIST);
        }

        itemRepository.deleteById(request.getItemId());
        return ItemDelete.Response.fromEntity(request.getItemId());
    }

    /*
     * 상품 프로모션 적용 내역 조회
     * 1. 상품 조회
     * 2. 상품에 등록된 프로모션 조회
     * 3. return 상품, 적용 프로모션, 프로모션 적용 결과
     */
    @Transactional
    public ItemSelectPromotionList.Response selectItemPromotionList(ItemSelectPromotionList.Request request){
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_ITEM_ID));

        List<Promotion> promotionList =
                promotionRepository
                        .selectItemPromotionByItemIdAndNowBetweenPromotionStartDateAndPromotionEndDate(request.getItemId(), java.sql.Timestamp.valueOf(LocalDateTime.now()))
                        .orElseThrow(() -> new InvalidRequestException(MessageUtils.NO_PROMOTION_APPLIED));

        double itemPrice = item.getItemPrice().doubleValue();
        double itemPriceAppliedPromotion = item.getItemPrice().doubleValue();
        int selectedPromotionIdx = -1;

        int promotionIdx = 0;
        for(Promotion promotion : promotionList) {
            double itemPriceAppliedCurPromotion = itemPrice;
            if(promotion.getDiscountAmount() != null){
                itemPriceAppliedCurPromotion -= promotion.getDiscountAmount();
            }
            else{
                itemPriceAppliedCurPromotion -= itemPriceAppliedCurPromotion * promotion.getDiscountRate();
            }

            if(itemPriceAppliedCurPromotion>0 && itemPriceAppliedPromotion>itemPriceAppliedCurPromotion){
                itemPriceAppliedPromotion = itemPriceAppliedCurPromotion;
                selectedPromotionIdx = promotionIdx;
            }
            promotionIdx++;
        }

        if(selectedPromotionIdx == -1){
            throw new InvalidRequestException(MessageUtils.NO_PROMOTION_APPLIED);
        }

        return ItemSelectPromotionList.Response.fromEntity(item, promotionList.get(selectedPromotionIdx), itemPriceAppliedPromotion);
    }
}
