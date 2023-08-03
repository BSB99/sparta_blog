package com.example.spartablog.postImage;

import com.example.spartablog.aws.s3.S3Service;
import com.example.spartablog.post.Post;
import com.example.spartablog.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class PostImageService {
    private final PostImageRepository postImageRepository;
    private final PostRepository postRepository;
    private final S3Service s3Service;
    public void saveFile(String fileName, Post post) {
        PostImage postImage = new PostImage(fileName, post);

        postImageRepository.save(postImage);

        post.getImages().add(postImage);
        postRepository.save(post);
    }

    public PostImage getPostImage(Long postId) {
        return postImageRepository.findByPostId(postId).orElseThrow(() -> new IllegalArgumentException("이미지가 존재하지 않습니다"));
    }

    public void updateFile(Long postId, MultipartFile[] files) throws IOException {
        PostImage postImage = getPostImage(postId);

        String[] oldImageList = postImage.getFileName().split(",");
        s3Service.deleteImage(oldImageList);

        String changeImage = s3Service.uploadImage(files);

        postImage.setFileName(changeImage);
    }

    public void deleteImages(Long postId) throws IOException {
        PostImage postImage = getPostImage(postId);
        s3Service.deleteImage(postImage.getFileName().split(","));
    }
}
