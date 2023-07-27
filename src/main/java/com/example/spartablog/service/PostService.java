package com.example.spartablog.service;

import com.example.spartablog.dto.PostResponseDto;
import com.example.spartablog.entity.Post;
import com.example.spartablog.entity.PostRequestDto;
import com.example.spartablog.entity.User;
import com.example.spartablog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public PostResponseDto creaetPost(User user, PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto, user);

        postRepository.save(post);

        return new PostResponseDto(post);
    }
}
