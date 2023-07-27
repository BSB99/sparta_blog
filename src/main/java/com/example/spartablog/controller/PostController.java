package com.example.spartablog.controller;

import com.example.spartablog.dto.PostDetailResponseDto;
import com.example.spartablog.dto.PostResponseDto;
import com.example.spartablog.dto.PostsResponseDto;
import com.example.spartablog.entity.Post;
import com.example.spartablog.entity.PostRequestDto;
import com.example.spartablog.entity.User;
import com.example.spartablog.security.UserDetailsImpl;
import com.example.spartablog.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public PostResponseDto createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postRequestDto) {
        return postService.createPost(userDetails.getUser(), postRequestDto);
    }

    @GetMapping("")
    public List<PostsResponseDto> getPosts() {
        return postService.getPosts();
    }

    @GetMapping("/{postId}")
    public PostDetailResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @PutMapping("/{postId}")
    public PostResponseDto updatePost(@PathVariable Long postId, @AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(postId, userDetails.getUser(), postRequestDto);
    }
}
