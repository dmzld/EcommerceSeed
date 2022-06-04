package com.example.EcommerceSeed.controller;

import com.example.EcommerceSeed.dto.PromotionCreate;
import com.example.EcommerceSeed.dto.PromotionDelete;
import com.example.EcommerceSeed.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


}
