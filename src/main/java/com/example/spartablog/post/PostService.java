package com.example.spartablog.post;

import com.example.spartablog.dto.ApiResponseDto;
import com.example.spartablog.dto.PostDetailResponseDto;
import com.example.spartablog.dto.PostResponseDto;
import com.example.spartablog.dto.PostsResponseDto;
import com.example.spartablog.dto.PostRequestDto;
import com.example.spartablog.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        List<Post> postList= postRepository.findByIdFetchJoin(postId);

        if (postList.isEmpty()) {
           throw new IllegalArgumentException("게시글이 존재하지 않습니다");
        }

        return new PostDetailResponseDto(postList.get(0));
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, PostRequestDto postRequestDto) {
        Post post = postCheck(postId);

        post.setTitle(postRequestDto.getTitle());
        post.setDescription(postRequestDto.getDescription());

        return new PostResponseDto(post);
    }

    public ApiResponseDto deletePost(Long postId) {
        Post post = postCheck(postId);

        postRepository.delete(post);

        return new ApiResponseDto("게시글 삭제 성공", 200);
    }

    public Post postCheck(Long id) {
        return postRepository.findById(id).orElseThrow(() -> {
            throw new IllegalArgumentException("게시글이 존재하지 않습니다.");
        });
    }

}
