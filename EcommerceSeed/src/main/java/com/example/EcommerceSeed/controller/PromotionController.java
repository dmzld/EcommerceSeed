package com.example.EcommerceSeed.controller;

import com.example.EcommerceSeed.dto.PromotionCreate;
import com.example.EcommerceSeed.dto.PromotionDelete;
import com.example.EcommerceSeed.dto.PromotionItemCreate;
import com.example.EcommerceSeed.dto.PromotionItemDelete;
import com.example.EcommerceSeed.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @RequestMapping("/createPromotion")
    public PromotionCreate.Response createPromotion(@RequestBody PromotionCreate.Request request){
        return promotionService.createPromotion(request);
    }

    @RequestMapping("/deletePromotion")
    public PromotionDelete.Response deletePromotion(@RequestBody PromotionDelete.Request request){
        return promotionService.deletePromotion(request);
    }

    @RequestMapping("/createPromotionItem")
    public PromotionItemCreate.Response createPromotionItem(@RequestBody PromotionItemCreate.Request request){
        return promotionService.createPromotionItem(request);
    }

    @RequestMapping("/deletePromotionItem")
    public PromotionItemDelete.Response deletePromotionItem(@RequestBody PromotionItemDelete.Request request){
        return promotionService.deletePromotionItem(request);
    }

}
