package com.example.spartablog.post;

import com.example.spartablog.dto.ApiResponseDto;
import com.example.spartablog.dto.PostDetailResponseDto;
import com.example.spartablog.dto.PostResponseDto;
import com.example.spartablog.dto.PostsResponseDto;
import com.example.spartablog.dto.PostRequestDto;
import com.example.spartablog.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("/search")
    public List<PostsResponseDto> searchPost(@RequestParam String title, Pageable pageable) {
        return postService.searchPost(title, pageable);
    }

    @PutMapping("/{postId}")
    public PostResponseDto updatePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId, @RequestBody PostRequestDto postRequestDto) {
        return postService.updatePost(postId, postRequestDto);
    }

    @DeleteMapping("/{postId}")
    public ApiResponseDto deletePost(@AuthenticationPrincipal UserDetailsImpl userDetails, @PathVariable Long postId) {
        return postService.deletePost(postId);
    }
}
