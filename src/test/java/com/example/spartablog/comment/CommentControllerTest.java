package com.example.spartablog.comment;

import com.example.spartablog.dto.CommentRequestDto;
import com.example.spartablog.security.UserDetailsImpl;
import com.example.spartablog.user.User;
import com.example.spartablog.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CommentControllerTest {
    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private UserRepository userRepository;

    private MockMvc mvc;

    private static final String BASE_URL = "/api/comment";

    @BeforeEach
    public void setup() {
        // 테스트를 위한 사용자 정보를 데이터베이스에서 조회.
        User user = userRepository.findByUsername("wer06099").orElseThrow(() -> new IllegalArgumentException("유저 정보가 존재하지 않습니다."));

        // 인증된 사용자 정보 설정
        UserDetails userDetails = new UserDetailsImpl(user);

        UsernamePasswordAuthenticationToken principal = new UsernamePasswordAuthenticationToken(
                userDetails, // 사용자 객체를 Principal로 설정
                null // 인증에 사용되는 credentials (비밀번호 등)은 필요 없으므로 null
        );
        SecurityContextHolder.getContext().setAuthentication(principal);

        // MockMvc 설정
        mvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }

    @Test
    @DisplayName("댓글 저장 테스트")
    void save_test() throws Exception {
        //given
        int postNo = 1;
        String content = "Test comment content";

        //when
        String body = mapper.writeValueAsString(
                new CommentRequestDto(content)
        );

        //then
        mvc.perform(post(BASE_URL+"/post/"+postNo)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("댓글 수정 테스트")
    void update_test() throws Exception {
        //given
        int commentNo = 1;
        String content = "Test comment content";

        //when
        String body = mapper.writeValueAsString(
                new CommentRequestDto(content)
        );

        //then
        mvc.perform(patch(BASE_URL+"/"+commentNo)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("댓글 삭제 테스트")
    void delete_test() throws Exception {
        //given
        int commentNo = 1;

        // when/then
        mvc.perform(delete(BASE_URL+"/"+commentNo))
                .andExpect(status().isOk());
    }
}
