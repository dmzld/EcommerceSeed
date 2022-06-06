package com.example.EcommerceSeed.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@EntityListeners(AuditingEntityListener.class)
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String itemName;
    private String itemType;
    private Long itemPrice;

    private Date itemDisplayStartDate;
    private Date itemDisplayEndDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item")
    private List<PromotionItem> promotionItems = new ArrayList<>();

}
