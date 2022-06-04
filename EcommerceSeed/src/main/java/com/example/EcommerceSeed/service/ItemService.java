package com.example.EcommerceSeed.service;

import com.example.EcommerceSeed.dto.ItemCreate;
import com.example.EcommerceSeed.dto.ItemDelete;
import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.exception.UserException;
import com.example.EcommerceSeed.repository.ItemRepository;
import com.example.EcommerceSeed.type.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final ItemRepository itemRepository;

    @Transactional
    public ItemCreate.Response createItem(ItemCreate.Request request){
        Item item = Item.builder()
                .itemName(request.getItemName())
                .itemType(request.getItemType())
                .itemDisplayStartDate(request.getItemDisplayStartDate())
                .itemDisplayEndDate(request.getItemDisplayEndDate())
                .build();

        return ItemCreate.Response.fromEntity(itemRepository.save(item));
    }

    @Transactional
    public ItemDelete.Response deleteItem(ItemDelete.Request request){
        itemRepository.delete(
                itemRepository.findById(request.getItemId()).orElseThrow(() -> new UserException(UserErrorCode.CANNOT_FIND_USER))
        );
        return ItemDelete.Response.fromEntity(request.getItemId());
    }
}
