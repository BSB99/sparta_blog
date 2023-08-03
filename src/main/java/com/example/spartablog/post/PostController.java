package com.example.spartablog.post;

import com.example.spartablog.dto.ApiResponseDto;
import com.example.spartablog.dto.PostDetailResponseDto;
import com.example.spartablog.dto.PostResponseDto;
import com.example.spartablog.dto.PostsResponseDto;
import com.example.spartablog.security.UserDetailsImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @PostMapping("")
    public PostResponseDto createPost(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestParam("postRequestDto") String requestDto, @RequestParam("file") MultipartFile[] files) throws JsonProcessingException {
        return postService.createPost(userDetails.getUser(), requestDto, files);
    }

    @GetMapping("")
    public List<PostsResponseDto> getPosts(Pageable pageable) {
        return postService.getPosts(pageable);
    }

    @GetMapping("/{postId}")
    public PostDetailResponseDto getPost(@PathVariable Long postId) {
        return postService.getPost(postId);
    }

    @GetMapping("/search")
    public List<PostsResponseDto> searchPost(@RequestParam String title, Pageable pageable) {
        return postService.searchPost(title, pageable);
    }

    @PutMapping("/{postId}")
    public PostResponseDto updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId,  @RequestParam("postRequestDto") String postRequestDto,  @RequestParam("file") MultipartFile[] files) throws IOException {
        return postService.updatePost(postId, postRequestDto, files);
    }

    @DeleteMapping("/{postId}")
    public ApiResponseDto deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) throws IOException {
        return postService.deletePost(postId);
    }
}
