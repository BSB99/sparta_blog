package com.example.spartablog.aop;

import com.example.spartablog.comment.Comment;
import com.example.spartablog.security.UserDetailsImpl;
import com.example.spartablog.comment.CommentService;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CommentValidationAspect {

    private final CommentService commentService;

    public CommentValidationAspect(CommentService commentService) {
        this.commentService = commentService;
    }

    @Pointcut("execution(public * com.example.spartablog.comment.CommentController.deleteComment(..)) || " +
            "execution(public * com.example.spartablog.comment.CommentController.updateComment(..))")
    public void commentMethods() {
    }

    @Before("commentMethods() && args(user, commentId, ..)")
    public void validateCommentOwner(UserDetailsImpl user, Long commentId) {
        Comment comment = commentService.commentCheck(commentId);

        if (!comment.getUser().getUsername().equals(user.getUsername())) { // -> 근데 이건 왜 통과함? ㅅ;빌아밀ㅇ;ㅈ맙리;ㅇㅂㅈ
            throw new IllegalArgumentException("댓글 작성한 회원이 아닙니다.");
        }
    }
}
