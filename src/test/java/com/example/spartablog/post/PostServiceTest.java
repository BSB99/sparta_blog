package com.example.spartablog.post;

import com.example.spartablog.dto.PostRequestDto;
import com.example.spartablog.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class) // 가짜 객체임을 명시하기 위해 작성
@TestInstance(TestInstance.Lifecycle.PER_METHOD) // serviceTest 간 변수 공유.
public class PostServiceTest {
    @InjectMocks
    PostService postService;

    @Mock
    PostRepository postRepository;

    @Mock
    UserRepository userRepository;

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    @DisplayName("insertPost 테스트")
    @Test
    void insertUser_ValidUser_Success() {
        // given
        var user = userRepository.findByUsername("wer06099").orElseThrow(() -> {
            throw new IllegalArgumentException();
        });

        PostRequestDto requestDto = new PostRequestDto("title", "description");

        // when
        postService.createPost(user, requestDto);

        // then
        //then(postRepository).should(times(1)).save(any(Post.class));
    }

}
