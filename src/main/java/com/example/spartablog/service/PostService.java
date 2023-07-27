package com.example.spartablog.service;

import com.example.spartablog.dto.PostResponseDto;
import com.example.spartablog.dto.PostsResponseDto;
import com.example.spartablog.entity.Post;
import com.example.spartablog.entity.PostRequestDto;
import com.example.spartablog.entity.User;
import com.example.spartablog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    public PostResponseDto createPost(User user, PostRequestDto postRequestDto) {
        Post post = new Post(postRequestDto, user);

        postRepository.save(post);

        return new PostResponseDto(post);
    }

    public List<PostsResponseDto> getPosts() {
        return postRepository.findAll().stream()
                .map(PostsResponseDto::new)
                .collect(Collectors.toList());
    }
}
