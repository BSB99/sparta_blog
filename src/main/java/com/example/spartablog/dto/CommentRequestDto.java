package com.example.spartablog.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentRequestDto {
    private String content;

    public CommentRequestDto(String content) {
        this.content = content;
    }
}
