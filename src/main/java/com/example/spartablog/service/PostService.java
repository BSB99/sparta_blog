package com.example.spartablog.service;

import com.example.spartablog.dto.PostDetailResponseDto;
import com.example.spartablog.dto.PostResponseDto;
import com.example.spartablog.dto.PostsResponseDto;
import com.example.spartablog.entity.Post;
import com.example.spartablog.entity.PostRequestDto;
import com.example.spartablog.entity.User;
import com.example.spartablog.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.parameters.P;
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
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc();

        return posts.stream().map(PostsResponseDto::new).collect(Collectors.toList());
    }

    public PostDetailResponseDto getPost(Long postId) {
        return new PostDetailResponseDto(postCheck(postId));
    }

    private Post postCheck(Long id) {
        return postRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        });
    }
}
