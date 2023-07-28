package com.example.spartablog.user;

import com.example.spartablog.dto.ApiResponseDto;
import com.example.spartablog.dto.SignInRequestDto;
import com.example.spartablog.dto.SignUpRequestDto;
import com.example.spartablog.user.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/auth/sign-up")
    public ApiResponseDto signUp(@Valid @RequestBody SignUpRequestDto requestDto) {
        return userService.signUp(requestDto);
    }

    @PostMapping("/auth/sign-in")
    public ApiResponseDto signIn(@Valid @RequestBody SignInRequestDto requestDto, HttpServletResponse response) {
        return userService.signIn(requestDto, response);
    }
}
