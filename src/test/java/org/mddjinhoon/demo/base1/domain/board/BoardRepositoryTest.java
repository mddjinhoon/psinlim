package org.mddjinhoon.demo.base1.domain.board;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        boardRepository.save(Board.builder()
            .title("제목")
            .content("내용")
            .author("글쓴이")
            .build());

        Board board = boardRepository.findAll().stream().filter(data -> data.getTitle().equals("제목")).findFirst().orElse(new Board());
        assertThat(board.getContent()).isEqualTo("내용");
    }

}
