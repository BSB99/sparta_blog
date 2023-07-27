package com.example.spartablog.service;

import com.example.spartablog.dto.CommentRequestDto;
import com.example.spartablog.dto.CommentResponseDto;
import com.example.spartablog.entity.Comment;
import com.example.spartablog.entity.Post;
import com.example.spartablog.entity.User;
import com.example.spartablog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    public CommentResponseDto createComment(User user, CommentRequestDto requestDto, Long postId) {
        Post post = postService.postCheck(postId);

        Comment comment = new Comment(requestDto, user, post);

        commentRepository.save(comment);

        return new CommentResponseDto(comment);
    }
}
