package com.example.spartablog.controller;

import com.example.spartablog.dto.PostResponseDto;
import com.example.spartablog.entity.PostRequestDto;
import com.example.spartablog.security.UserDetailsImpl;
import com.example.spartablog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public PostResponseDto createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postRequestDto) {
        return postService.creaetPost(userDetails.getUser(), postRequestDto);
    }
}
