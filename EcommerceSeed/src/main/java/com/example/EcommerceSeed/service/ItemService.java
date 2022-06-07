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

    @Transactional
    public ItemSelectPromotionList.Response selectItemPromotionList(ItemSelectPromotionList.Request request){
        Item item = itemRepository.findById(request.getItemId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_ITEM_ID));

        List<Promotion> promotionList =
                promotionRepository
                        .selectItemPromotionByItemIdAndNowBetweenPromotionStartDateAndPromotionEndDate(request.getItemId(), java.sql.Timestamp.valueOf(LocalDateTime.now()))
                        .orElseThrow(() -> new InvalidRequestException(MessageUtils.NO_PROMOTION_APPLIED));

        Double itemPrice = item.getItemPrice().doubleValue();
        Double itemPriceAppliedPromotion = item.getItemPrice().doubleValue();
        Integer selectedPromotionIdx = -1;
        Integer promotionIdx = 0;

        for(Promotion promotion : promotionList) {
            Double itemPriceAppliedCurPromotion = itemPrice;
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
