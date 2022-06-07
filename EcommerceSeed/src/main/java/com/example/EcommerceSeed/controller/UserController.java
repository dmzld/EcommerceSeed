package com.example.EcommerceSeed.controller;

import com.example.EcommerceSeed.dto.UserCreate;
import com.example.EcommerceSeed.dto.UserDelete;
import com.example.EcommerceSeed.dto.UserSelectItemList;
import com.example.EcommerceSeed.dto.response.DataResponse;
import com.example.EcommerceSeed.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/createUser")
    public DataResponse<UserCreate.Response> createUser(@RequestBody @Valid UserCreate.Request request) {
        return new DataResponse<>(userService.createUser(request));
    }

    @DeleteMapping("/deleteUser")
    public DataResponse<UserDelete.Response> deleteUser(@RequestBody @Valid UserDelete.Request request){
        return new DataResponse<>(userService.deleteUser(request));
    }

    @GetMapping("/selectUserItemList")
    public DataResponse<List<UserSelectItemList.Response>> selectUserItemList(@RequestBody @Valid UserSelectItemList.Request request){
        return new DataResponse<>(userService.selectUserItemList(request));
    }
}
