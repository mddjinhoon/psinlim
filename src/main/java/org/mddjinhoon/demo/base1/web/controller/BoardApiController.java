package org.mddjinhoon.demo.base1.web.controller;

import lombok.RequiredArgsConstructor;
import org.mddjinhoon.demo.base1.service.BoardService;
import org.mddjinhoon.demo.base1.web.dto.BoardResponseDto;
import org.mddjinhoon.demo.base1.web.dto.BoardSaveRequestDto;
import org.mddjinhoon.demo.base1.web.dto.BoardUpdateRequestDto;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.Charset;

@RequiredArgsConstructor // 필드값을 final 처리해야 생성자로 Bean 처리할 수 있음
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/board/save")
    public Long save(@RequestBody BoardSaveRequestDto requestDto) {
        /*
        HttpHeaders headers= new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
        // return ResponseEntity.ok(boardService.save(requestDto));
        return new ResponseEntity<>(requestDto, headers ,HttpStatus.OK);
         */
        return boardService.save(requestDto);
    }

    @GetMapping("/board/{id}")
    public BoardResponseDto findById(@PathVariable Long id) {
        return boardService.findById(id);
    }

    @PutMapping("/board/update")
    public Long update(@RequestBody BoardUpdateRequestDto requestDto) {
        return boardService.update(requestDto);
    }
}