package org.mddjinhoon.demo.base1.domain.board;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mddjinhoon.demo.base1.web.dto.BoardSaveRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    BoardRepository boardRepository;

    @After
    public void after() {

    }

    @Test
    public void 게시글저장() throws Exception {
        boardRepository.save(Board.builder()  // save()는 id값에 해당하는 데이터가 이미 있다면 update, 그렇지 않으면 insert를 실행
            .title("제목")
            .content("내용")
            .author("글쓴이")
            .build());

        Board board = boardRepository.findAll().stream().filter(data -> data.getTitle().equals("제목")).findFirst().orElse(new Board());
        assertThat(board.getContent()).isEqualTo("내용");
    }

    @Test
    public void BaseTimeEntity등록() throws Exception {
        LocalDateTime localDateTime = LocalDateTime.of(2019,6,4,0,0,0);
        boardRepository.save(Board.builder()
            .title("title").content("content").author("author").build());

        Board board = boardRepository.findAll().stream().findFirst().orElseThrow(() ->
                new IllegalArgumentException("데이터가 없습니다."));

        assertThat(board.getCreateDate()).isAfter(localDateTime);
        assertThat(board.getModifiedDate()).isAfter(localDateTime);
    }
}
