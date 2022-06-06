package com.example.EcommerceSeed.repository;

import java.util.List;

import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
import com.example.EcommerceSeed.entity.PromotionItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PromotionItemRepository extends JpaRepository<PromotionItem, Long> {

    @Query(value = "SELECT * FROM PROMOTION_ITEM WHERE PROMOTION_ITEM.PROMOTION_ID = :promotionId AND PROMOTION_ITEM.ITEM_ID = :itemId", nativeQuery = true)
    public PromotionItem existsByPromotionIdAndItemId(Long promotionId, Long itemId);

    @Modifying
    @Query(value = "DELETE FROM PROMOTION_ITEM WHERE PROMOTION_ITEM.PROMOTION_ID = :promotionId AND PROMOTION_ITEM.ITEM_ID = :itemId", nativeQuery = true)
    public void deletePromotionItemByPromotionIdAndItemId(Long promotionId, Long itemId);

}
