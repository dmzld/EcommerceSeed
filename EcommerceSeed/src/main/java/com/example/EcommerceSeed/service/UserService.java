package com.example.EcommerceSeed.service;

import com.example.EcommerceSeed.dto.UserCreate;
import com.example.EcommerceSeed.dto.UserDelete;
import com.example.EcommerceSeed.dto.UserSelectItemList;
import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.Promotion;
import com.example.EcommerceSeed.entity.User;
import com.example.EcommerceSeed.exception.UserException;
import com.example.EcommerceSeed.repository.ItemRepository;
import com.example.EcommerceSeed.repository.PromotionRepository;
import com.example.EcommerceSeed.repository.UserRepository;
import com.example.EcommerceSeed.type.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final PromotionRepository promotionRepository;

    @Transactional
    public UserCreate.Response createUser(UserCreate.Request request){
        // validate

        User user = User.builder()
                .userName(request.getUserName())
                .userType(request.getUserType())
                .userStat(request.getUserStat())
                .build();

        return UserCreate.Response.fromEntity(userRepository.save(user));
    }

    @Transactional
    public UserDelete.Response deleteUser(UserDelete.Request request){
        userRepository.delete(
                userRepository.findById(request.getUserId()).orElseThrow(() -> new UserException(UserErrorCode.CANNOT_FIND_USER))
        );
        return UserDelete.Response.fromEntity(request.getUserId());
    }

    @Transactional
    public List<UserSelectItemList.Response> selectUserItemList(UserSelectItemList.Request request){
        User user = userRepository.findById(request.getUserId()).orElse(null);

        List<Item> dummyList = new ArrayList<>();
        List<UserSelectItemList.Response> responseList = new ArrayList<>();
        if(user == null || user.getUserStat().equals("탈퇴")){
            for(Item dummy : dummyList){
                responseList.add(UserSelectItemList.Response.fromEntity(dummy));
            }
            return responseList;
        }
        else{
            List<Item> itemList = itemRepository.findItemsByItemTypeAndNowBetweenItemDisplayStartDateAndItemDisplayEndDate(user.getUserType().equals("일반")?"일반":"기업회원상품", java.sql.Timestamp.valueOf(LocalDateTime.now()));
            for(Item item : itemList){
                responseList.add(UserSelectItemList.Response.fromEntity(item));
            }
            return responseList;
        }
    }
}
