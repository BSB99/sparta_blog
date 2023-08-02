package com.example.spartablog.dto;

import lombok.*;

@Getter
@NoArgsConstructor
public class PostRequestDto {
    private String title;
    private String description;

    public PostRequestDto(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
