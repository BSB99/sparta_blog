package com.example.spartablog.post;

import java.util.List;

public interface PostCustomRepository {
    List<Post> findByIdFetchJoin(Long id);
}
