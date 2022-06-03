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
@EntityListeners(AuditingEntityListener.class)
public class Promotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long promotionId;

    private String promotionNm;
    private Long discountAmount;
    private Double discountRate;
    private Date promotionStartDate;
    private Date promotionEndDate;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "promotion")
    private List<ItemPromotion>itemPromotions = new ArrayList<>();
}
