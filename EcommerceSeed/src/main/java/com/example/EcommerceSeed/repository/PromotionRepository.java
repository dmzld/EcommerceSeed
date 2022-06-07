package com.example.EcommerceSeed.repository;

import com.example.EcommerceSeed.entity.Promotion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    @Query(value = "SELECT B.* FROM PROMOTION_ITEM A, PROMOTION B WHERE A.ITEM_ID = :itemId AND A.PROMOTION_ID = B.PROMOTION_ID AND :now BETWEEN B.PROMOTION_START_DATE AND B.PROMOTION_END_DATE", nativeQuery = true)
    public Optional<List<Promotion>> selectItemPromotionByItemIdAndNowBetweenPromotionStartDateAndPromotionEndDate(Long itemId, Date now);
}
