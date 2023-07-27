package com.example.spartablog.service;

import com.example.spartablog.dto.ApiResponseDto;
import com.example.spartablog.dto.UserRequestDto;
import com.example.spartablog.entity.User;
import com.example.spartablog.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public ApiResponseDto signUp(UserRequestDto requestDto) {
        if (!requestDto.isPasswordValid()) {
            return new ApiResponseDto("비밀번호에는 닉네임을 포함할 수 없습니다.",400);
        }

        if (!requestDto.getPassword().equals(requestDto.getCheckPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        User user = new User(requestDto);

        userRepository.save(user);

        return new ApiResponseDto("회원가입 성공", 201);
    }

    public ApiResponseDto signIn(UserRequestDto requestDto) {
        return null;
    }
}
