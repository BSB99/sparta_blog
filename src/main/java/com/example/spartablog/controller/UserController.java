package com.example.spartablog.controller;

import com.example.spartablog.dto.ApiResponseDto;
import com.example.spartablog.dto.UserRequestDto;
import com.example.spartablog.entity.User;
import com.example.spartablog.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("sign-up")
    public ApiResponseDto signUp(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.signUp(requestDto);
    }

    @PostMapping("sign-in")
    public ApiResponseDto signIn(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.signIn(requestDto);
    }
}
