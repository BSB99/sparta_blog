package com.example.spartablog.aop;

import com.example.spartablog.entity.Comment;
import com.example.spartablog.entity.Post;
import com.example.spartablog.entity.User;
import com.example.spartablog.repository.PostRepository;
import com.example.spartablog.security.UserDetailsImpl;
import com.example.spartablog.service.PostService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class PostValidationAspect {
    private final PostService postService;

    public PostValidationAspect(PostService postService) {
        this.postService = postService;
    }

    @Pointcut("execution(public * com.example.spartablog.controller.PostController.updatePost(..)) || " +
            "execution(public * com.example.spartablog.controller.PostController.deletePost(..))")
    public void postMethods() {
    }

    @Before("postMethods() && args(user, postId, ..)")
    public void validatePostOwner(UserDetailsImpl user, Long postId) {
        System.out.println(user.getUsername());

        Post post = postService.postCheck(postId);

        if (!post.getUser().getUsername().equals(user.getUsername())) {
            throw new IllegalArgumentException("게시글을 작성한 회원이 아닙니다.");
        }
    }
}
