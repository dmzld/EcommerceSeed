package com.example.EcommerceSeed.service;

import com.example.EcommerceSeed.dto.ItemCreate;
import com.example.EcommerceSeed.dto.ItemDelete;
import com.example.EcommerceSeed.dto.ItemSelectPromotionList;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;
    private final PromotionItemRepository promotionItemRepository;
    private final PromotionRepository promotionRepository;

    @Transactional
    public ItemCreate.Response createItem(ItemCreate.Request request){
        Item item = Item.builder()
                .itemName(request.getItemName())
                .itemType(request.getItemType())
                .itemPrice(request.getItemPrice())
                .itemDisplayStartDate(request.getItemDisplayStartDate())
                .itemDisplayEndDate(request.getItemDisplayEndDate())
                .build();

        return ItemCreate.Response.fromEntity(itemRepository.save(item));
    }

    @Transactional
    public ItemDelete.Response deleteItem(ItemDelete.Request request){
        itemRepository.delete(
                itemRepository.findById(request.getItemId()).orElseThrow(() -> new UserException(UserErrorCode.CANNOT_FIND_USER))
        );
        return ItemDelete.Response.fromEntity(request.getItemId());
    }

    @Transactional
    public ItemSelectPromotionList.Response selectItemPromotionList(ItemSelectPromotionList.Request request){
        Item item = itemRepository.findById(request.getItemId()).orElse(null);

        if(item == null){

        }
        else{
            List<Promotion> promotionList = promotionRepository.selectItemPromotionByItemIdAndNowBetweenPromotionStartDateAndPromotionEndDate(request.getItemId(), java.sql.Timestamp.valueOf(LocalDateTime.now()));
            //List<Promotion> promotionList = promotionRepository.findAll();
            Double itemPrice = item.getItemPrice().doubleValue();
            Double itemPriceAppliedPromotion = item.getItemPrice().doubleValue();
            Integer selectedPromotionId = -1;
            Integer promotionIdx = 0;

            for(Promotion promotion : promotionList) {
                System.out.println(promotion.toString());
                Double itemPriceAppliedCurPromotion = itemPrice;
                if(promotion.getDiscountAmount() != null){
                    itemPriceAppliedCurPromotion -= promotion.getDiscountAmount();
                }
                else{
                    itemPriceAppliedCurPromotion -= itemPriceAppliedCurPromotion * promotion.getDiscountRate();
                }

                if(itemPriceAppliedCurPromotion>0 && itemPriceAppliedPromotion>itemPriceAppliedCurPromotion){
                    itemPriceAppliedPromotion = itemPriceAppliedCurPromotion;
                    selectedPromotionId = promotionIdx;
                }
                promotionIdx++;
            }

            if(selectedPromotionId != -1){
                return ItemSelectPromotionList.Response.fromEntity(item, promotionList.get(selectedPromotionId), itemPriceAppliedPromotion);
            }
        }
        return ItemSelectPromotionList.Response.fromEntity(new Item(), new Promotion(), 0.0);
    }
}
