package com.example.spartablog.post;

import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostCustomRepository {
    List<Post> findByTitleFetchJoin(String title, Pageable pageable);
}
