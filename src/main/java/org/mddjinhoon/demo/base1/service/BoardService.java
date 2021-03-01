package org.mddjinhoon.demo.base1.service;

import lombok.RequiredArgsConstructor;
import org.mddjinhoon.demo.base1.domain.board.Board;
import org.mddjinhoon.demo.base1.domain.board.BoardRepository;
import org.mddjinhoon.demo.base1.web.dto.BoardResponseDto;
import org.mddjinhoon.demo.base1.web.dto.BoardSaveRequestDto;
import org.mddjinhoon.demo.base1.web.dto.BoardUpdateRequestDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public Long save(BoardSaveRequestDto requestDto) {
        return boardRepository.save(requestDto.toEntity()).getId();
    }

    public BoardResponseDto findById(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("데이터가 없습니다."));

        return BoardResponseDto.builder().entity(board).build();
    }

    @Transactional
    public Long update(BoardUpdateRequestDto requestDto) {
        Board board = boardRepository.findById(requestDto.getId()).orElseThrow(() ->
                new IllegalArgumentException("데이터가 없습니다."));
        board.update(requestDto.getTitle(), requestDto.getContent()); // 더티체킹
        return requestDto.getId();
    }
}
