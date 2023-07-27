package com.example.spartablog.dto;

import com.example.spartablog.entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private String username;

    public PostDetailResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.username = post.getUser().getUsername();
        this.createAt = post.getCreatedAt();
    }
}
