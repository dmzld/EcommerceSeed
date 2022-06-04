package com.example.EcommerceSeed.service;

import com.example.EcommerceSeed.dto.UserCreate;
import com.example.EcommerceSeed.dto.UserDelete;
import com.example.EcommerceSeed.entity.User;
import com.example.EcommerceSeed.exception.UserException;
import com.example.EcommerceSeed.repository.UserRepository;
import com.example.EcommerceSeed.type.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
}
