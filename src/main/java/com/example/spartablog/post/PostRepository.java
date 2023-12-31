package com.example.spartablog.post;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, PostCustomRepository {
    List<Post> findAllByOrderByCreatedAtDesc(Pageable pageable);
}
