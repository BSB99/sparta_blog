package com.example.spartablog.comment;

import com.example.spartablog.dto.CommentRequestDto;
import com.example.spartablog.entity.Timestamped;
import com.example.spartablog.user.User;
import com.example.spartablog.post.Post;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comments")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Comment(CommentRequestDto comment, User user, Post post) {
        this.content = comment.getContent();
        this.user = user;
        this.post = post;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
