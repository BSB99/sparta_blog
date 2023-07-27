package com.example.spartablog.controller;

import com.example.spartablog.dto.CommentRequestDto;
import com.example.spartablog.dto.CommentResponseDto;
import com.example.spartablog.security.UserDetailsImpl;
import com.example.spartablog.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/post/{postId}")
    public CommentResponseDto createComment(@AuthenticationPrincipal UserDetailsImpl userDetails, @RequestBody CommentRequestDto requestDto, @PathVariable Long postId) {
        return commentService.createComment(userDetails.getUser(), requestDto, postId);
    }
}
