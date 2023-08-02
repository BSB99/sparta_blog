package com.example.spartablog.post;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static com.example.spartablog.post.QPost.post;
import static com.example.spartablog.user.QUser.user;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> findByTitleFetchJoin(String title, Pageable pageable) {
        return jpaQueryFactory
                .selectFrom(post)
                .leftJoin(post.user, user)
                .where(post.title.like("%" + title + "%"))
                .offset(pageable.getOffset()) // 페이지 시작 오프셋
                .limit(pageable.getPageSize()) // 페이지 크기
                .fetch();
    }
}
