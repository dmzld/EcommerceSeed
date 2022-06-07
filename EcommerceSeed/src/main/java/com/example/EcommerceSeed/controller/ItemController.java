package com.example.EcommerceSeed.controller;

import com.example.EcommerceSeed.dto.ItemCreate;
import com.example.EcommerceSeed.dto.ItemDelete;
import com.example.EcommerceSeed.dto.ItemSelectPromotionList;
import com.example.EcommerceSeed.dto.UserCreate;
import com.example.EcommerceSeed.dto.response.DataResponse;
import com.example.EcommerceSeed.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/createItem")
    public DataResponse<ItemCreate.Response> createItem(@RequestBody @Valid ItemCreate.Request request){
        return new DataResponse(itemService.createItem(request));
    }

    @DeleteMapping("/deleteItem")
    public DataResponse<ItemDelete.Response> deleteItem(@RequestBody @Valid ItemDelete.Request request){
        return new DataResponse(itemService.deleteItem(request));
    }

    @GetMapping("/selectItemPromotionList")
    public DataResponse<ItemSelectPromotionList.Response> selectItemPromotionList(@RequestBody @Valid ItemSelectPromotionList.Request request){
        return new DataResponse(itemService.selectItemPromotionList(request));
    }
}
