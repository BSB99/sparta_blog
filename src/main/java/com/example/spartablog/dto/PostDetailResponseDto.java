package com.example.spartablog.dto;

import com.example.spartablog.comment.Comment;
import com.example.spartablog.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private String username;
    private Set<CommentResponseDto> commentList = new LinkedHashSet<>();

    public PostDetailResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.username = post.getUser().getUsername();
        this.createAt = post.getCreatedAt();
        for(Comment comment : post.getCommentList()) {
            commentList.add(new CommentResponseDto(comment));
        }
    }
}
