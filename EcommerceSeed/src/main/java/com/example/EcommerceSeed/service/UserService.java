package com.example.EcommerceSeed.service;

import com.example.EcommerceSeed.dto.UserCreate;
import com.example.EcommerceSeed.dto.UserDelete;
import com.example.EcommerceSeed.dto.UserSelectItemList;
import com.example.EcommerceSeed.entity.Item;
import com.example.EcommerceSeed.entity.User;
import com.example.EcommerceSeed.exception.InvalidRequestException;
import com.example.EcommerceSeed.repository.ItemRepository;
import com.example.EcommerceSeed.repository.PromotionRepository;
import com.example.EcommerceSeed.repository.UserRepository;
import com.example.EcommerceSeed.util.MessageUtils;
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

    /*
     * 사용자 생성
     * 1. 사용자 생성
     * 2. return 생성된 사용자
     */
    @Transactional
    public UserCreate.Response createUser(UserCreate.Request request){
        return UserCreate.Response.fromEntity(userRepository.save(User.builder()
                .userName(request.getUserName())
                .userType(request.getUserType())
                .userStat(request.getUserStat())
                .build()));
    }

    /*
     * 사용자 삭제
     * 1. 사용자 조회 및 삭제
     * 2. return 삭제된 사용자 ID
     */
    @Transactional
    public UserDelete.Response deleteUser(UserDelete.Request request){
        userRepository.delete(
                userRepository.findById(request.getUserId())
                        .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_USER_ID)));
        return UserDelete.Response.fromEntity(request.getUserId());
    }

    /*
     * 사용자가 구매할 수 있는 상품
     * 1. 사용자 조회
     * 2. 사용자 type validation
     * 3. 구매할 수 있는 상품 조회
     * 4. return 사용자가 구매할 수 있는 상품 리스트
     */
    @Transactional
    public List<UserSelectItemList.Response> selectUserItemList(UserSelectItemList.Request request){
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new InvalidRequestException(MessageUtils.CANNOT_FIND_USER_ID));

        List<UserSelectItemList.Response> responseList = new ArrayList<>();
        if(user == null || user.getUserStat().equals("탈퇴")){
            throw new InvalidRequestException(MessageUtils.INVALID_USER_STAT);
        }
        else{
            List<Item> itemList = itemRepository
                    .findItemsByItemTypeAndNowBetweenItemDisplayStartDateAndItemDisplayEndDate(user.getUserType().equals("일반")?"일반":"기업회원상품", java.sql.Timestamp.valueOf(LocalDateTime.now()));

            if(itemList.isEmpty()){
                throw new InvalidRequestException(MessageUtils.NO_ITEM_SEARCHED);
            }
            for(Item item : itemList){
                responseList.add(UserSelectItemList.Response.fromEntity(item));
            }
        }
        return responseList;
    }
}
