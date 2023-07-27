package com.example.spartablog.dto;

import com.example.spartablog.entity.Post;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PostsResponseDto {
    private Long id;
    private String title;
    private LocalDateTime createAt;
    private String username;

    public PostsResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.createAt = post.getCreatedAt();
        this.username = post.getUser().getUsername();
    }
}
