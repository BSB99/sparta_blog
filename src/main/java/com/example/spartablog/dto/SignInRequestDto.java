package com.example.spartablog.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class SignInRequestDto {
    @Pattern(regexp = "^[a-zA-Z0-9]{3,}$", message = "닉네임은 최소 3자 이상, 알파벳 대소문자(a~z, A~Z), 숫자(0~9)로 구성되어야 합니다.")
    private String username;

    @Pattern(regexp = ".{4,}", message = "비밀번호는 최소 4자 이상이어야 합니다.")
    private String password;
}
