package com.example.EcommerceSeed.controller;

import com.example.EcommerceSeed.dto.PromotionCreate;
import com.example.EcommerceSeed.dto.PromotionDelete;
import com.example.EcommerceSeed.dto.PromotionItemCreate;
import com.example.EcommerceSeed.dto.PromotionItemDelete;
import com.example.EcommerceSeed.dto.response.DataResponse;
import com.example.EcommerceSeed.service.PromotionService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/promotion")
@RequiredArgsConstructor
public class PromotionController {
    private final PromotionService promotionService;

    @PostMapping("/createPromotion")
    public DataResponse<PromotionCreate.Response> createPromotion(@RequestBody @Valid PromotionCreate.Request request){
        return new DataResponse(promotionService.createPromotion(request));
    }

    @DeleteMapping("/deletePromotion")
    public DataResponse<PromotionDelete.Response> deletePromotion(@RequestBody @Valid PromotionDelete.Request request){
        return new DataResponse(promotionService.deletePromotion(request));
    }

    @PostMapping("/createPromotionItem")
    public DataResponse<PromotionItemCreate.Response> createPromotionItem(@RequestBody @Valid PromotionItemCreate.Request request){
        return new DataResponse(promotionService.createPromotionItem(request));
    }

    @DeleteMapping("/deletePromotionItem")
    public DataResponse<PromotionItemDelete.Response> deletePromotionItem(@RequestBody @Valid PromotionItemDelete.Request request){
        return new DataResponse(promotionService.deletePromotionItem(request));
    }

}
