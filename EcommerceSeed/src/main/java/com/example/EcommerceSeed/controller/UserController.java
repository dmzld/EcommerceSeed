package com.example.EcommerceSeed.controller;

import com.example.EcommerceSeed.dto.UserCreate;
import com.example.EcommerceSeed.dto.UserDelete;
import com.example.EcommerceSeed.dto.UserSelectItemList;
import com.example.EcommerceSeed.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/createUser")
    public UserCreate.Response createUser(@RequestBody UserCreate.Request request) {
        return userService.createUser(request);
    }

    @DeleteMapping("/deleteUser")
    public UserDelete.Response deleteUser(@RequestBody UserDelete.Request request){
        return userService.deleteUser(request);
    }

    @GetMapping("/selectUserItemList")
    public UserSelectItemList.Response selectUserItemList(@RequestBody UserSelectItemList.Request request){
        return userService.selectUserItemList(request);
    }
}
