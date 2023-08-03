package com.example.spartablog.post;

import com.example.spartablog.aws.s3.S3Service;
import com.example.spartablog.dto.*;
import com.example.spartablog.postImage.PostImageService;
import com.example.spartablog.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final S3Service s3Service;
    private final PostImageService postImageService;
    public PostResponseDto createPost(User user, String postRequestDto, MultipartFile[] files) throws JsonProcessingException {
        PostRequestDto requestDto = conversionDto(postRequestDto);
        Post post = new Post(requestDto.getTitle(), requestDto.getDescription(), user);

        String fileNames = s3Service.uploadImage(files);
        postRepository.save(post);

        // 이미지 파일 처리
        postImageService.saveFile(fileNames, post);

        return new PostResponseDto(post);
    }

    public List<PostsResponseDto> getPosts(Pageable pageable) {
        List<Post> posts = postRepository.findAllByOrderByCreatedAtDesc(pageable);

        return posts.stream().map(PostsResponseDto::new).collect(Collectors.toList());
    }

    public PostDetailResponseDto getPost(Long postId) {
        return new PostDetailResponseDto(postCheck(postId));
    }

    @Transactional
    public PostResponseDto updatePost(Long postId, String requestDto, MultipartFile[] files) throws IOException {
        Post post = postCheck(postId);
        PostRequestDto postRequestDto = conversionDto(requestDto);

        postImageService.updateFile(postId,files);

        post.setTitle(postRequestDto.getTitle());
        post.setDescription(postRequestDto.getDescription());

        return new PostResponseDto(post);
    }

    public ApiResponseDto deletePost(Long postId) throws IOException {
        Post post = postCheck(postId);

        postImageService.deleteImages(postId);
        postRepository.delete(post);

        return new ApiResponseDto("게시글 삭제 성공", 200);
    }

    public Post postCheck(Long id) {
        return postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("게시글이 존재하지 않습니다."));
    }

    public List<PostsResponseDto> searchPost(String title, Pageable pageable) {
        List<Post> posts = postRepository.findByTitleFetchJoin(title, pageable);

        return posts.stream().map(PostsResponseDto::new).collect(Collectors.toList());
    }

    public PostRequestDto conversionDto(String requestDto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(requestDto, PostRequestDto.class);
    }
}
