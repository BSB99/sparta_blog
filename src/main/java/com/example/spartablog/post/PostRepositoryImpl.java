package com.example.spartablog.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.example.spartablog.post.QPost.post;
import static com.example.spartablog.user.QUser.user;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> findByIdFetchJoin(Long id) {
        return jpaQueryFactory
                .selectFrom(post)
                .leftJoin(post.user, user) // Join User entity
                .where(post.id.eq(id))
                .fetch();
    }
}
