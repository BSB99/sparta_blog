package com.example.spartablog.service;

import ch.qos.logback.core.sift.AppenderFactoryUsingSiftModel;
import com.example.spartablog.dto.ApiResponseDto;
import com.example.spartablog.dto.CommentRequestDto;
import com.example.spartablog.dto.CommentResponseDto;
import com.example.spartablog.entity.Comment;
import com.example.spartablog.entity.Post;
import com.example.spartablog.entity.User;
import com.example.spartablog.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public ApiResponseDto deleteComment(User user, Long commentId) {
        Comment comment = commentCheck(commentId);

        commentRepository.delete(comment);

        return new ApiResponseDto("댓글 삭제 성공", 200);
    }

    @Transactional
    public CommentResponseDto updateComment(User user, Long commentId, CommentRequestDto commentRequestDto) {
        Comment comment = commentCheck(commentId);

        comment.setContent(commentRequestDto.getContent());

        return new CommentResponseDto(comment);
    }

    public Comment commentCheck(Long commentId) {
        return commentRepository.findById(commentId).orElseThrow(() -> {
            throw new IllegalArgumentException("댓글이 존재하지 않습니다.");
        });
    }
}
