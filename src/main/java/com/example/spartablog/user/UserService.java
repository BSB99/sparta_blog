package com.example.spartablog.user;

import com.example.spartablog.dto.ApiResponseDto;
import com.example.spartablog.dto.SignInRequestDto;
import com.example.spartablog.dto.SignUpRequestDto;
import com.example.spartablog.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public ApiResponseDto signUp(SignUpRequestDto requestDto) {
        if (!requestDto.isPasswordValid()) {
            return new ApiResponseDto("비밀번호에는 닉네임을 포함할 수 없습니다.",400);
        }

        if (!requestDto.getPassword().equals(requestDto.getCheckPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다");
        }

        userRepository.findByUsername(requestDto.getUsername())
                .ifPresent(user -> {
                    throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
                });

        requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

        userRepository.save(new User(requestDto.getUsername(), requestDto.getPassword()));

        return new ApiResponseDto("회원가입 성공", 201);
    }

    public ApiResponseDto signIn(SignInRequestDto requestDto, HttpServletResponse response) {

        User user = userRepository.findByUsername(requestDto.getUsername()).orElseThrow(
                () -> new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요.")
        );

        if(!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("닉네임 또는 패스워드를 확인해주세요.");
        }

        String token = jwtUtil.createToken(user.getUsername());
        jwtUtil.addJwtToCookie(token, response);

        return new ApiResponseDto("로그인 성공", 200);
    }
}
