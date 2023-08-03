package com.example.spartablog.post;

import com.example.spartablog.comment.Comment;
import com.example.spartablog.entity.Timestamped;
import com.example.spartablog.postImage.PostImage;
import com.example.spartablog.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "posts")
public class Post extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE)
    private Set<Comment> commentList = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post", cascade = {CascadeType.ALL,CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private List<PostImage> images = new ArrayList<>();

    public Post(String title, String description, User user) {
        this.title = title;
        this.description = description;
        this.user = user;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
