package com.example.EcommerceSeed.repository;

import com.example.EcommerceSeed.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "SELECT * FROM ITEM WHERE ITEM.ITEM_TYPE = :itemType AND :now BETWEEN ITEM.ITEM_DISPLAY_START_DATE AND ITEM.ITEM_DISPLAY_END_DATE", nativeQuery = true)
    public List<Item> findItemsByItemType(String itemType, Date now);
}
