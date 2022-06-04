package com.example.EcommerceSeed.controller;

import com.example.EcommerceSeed.dto.ItemCreate;
import com.example.EcommerceSeed.dto.ItemDelete;
import com.example.EcommerceSeed.dto.UserCreate;
import com.example.EcommerceSeed.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/item")
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @PostMapping("/createItem")
    public ItemCreate.Response createItem(@RequestBody ItemCreate.Request request){
        return itemService.createItem(request);
    }

    @DeleteMapping("/deleteItem")
    public ItemDelete.Response deleteItem(@RequestBody ItemDelete.Request request){
        return itemService.deleteItem(request);
    }
}
