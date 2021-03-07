package org.mddjinhoon.demo.base1.web.controller;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mddjinhoon.demo.base1.domain.board.Board;
import org.mddjinhoon.demo.base1.domain.board.BoardRepository;
import org.mddjinhoon.demo.base1.web.dto.BoardSaveRequestDto;
import org.mddjinhoon.demo.base1.web.dto.BoardUpdateRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BoardApiControllerTest {

    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private BoardRepository boardRepository;
    @After
    public void clean() throws Exception {
        boardRepository.deleteAll();
    }

    @Test
    public void Board등록테스트() throws Exception {
        BoardSaveRequestDto requestDto = BoardSaveRequestDto.builder()
                .title("제목").content("내용").author("글쓴이").build();
        String url = "http://localhost:" + port + "/board/save";
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);
        Board board = boardRepository.findAll().stream().findFirst().orElse(new Board());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);
        assertThat(board.getTitle()).isEqualTo("제목");
        assertThat(board.getContent()).isEqualTo("내용");
    }

    @Test
    public void Board업데이트테스트() throws Exception {
        Board board = boardRepository.save(BoardSaveRequestDto.builder().title("title1").content("content1").author("author1").build().toEntity());
        BoardUpdateRequestDto requestDto = BoardUpdateRequestDto.builder().id(board.getId()).title("title2").content("content2").author("author2").build();
        String url = "http://localhost:" + port + "/board/update";
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, new HttpEntity<>(requestDto), Long.class);
        Board updatedBoard = boardRepository.findAll().stream().findFirst().orElse(new Board());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(board.getId());
        assertThat(updatedBoard.getTitle()).isEqualTo("title2");
        assertThat(updatedBoard.getContent()).isEqualTo("content2");

    }
}