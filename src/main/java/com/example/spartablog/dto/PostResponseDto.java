package com.example.spartablog.dto;

import com.example.spartablog.post.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    private Long id;
    private String title;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updatedAt;
    
    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.description = post.getDescription();
        this.createAt = post.getCreatedAt();
        this.updatedAt = post.getUpdatedAt();
    }

}
