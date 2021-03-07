package org.mddjinhoon.demo.base1.web.dto;

import lombok.Builder;
import lombok.Getter;
import org.mddjinhoon.demo.base1.domain.board.Board;

@Getter
public class BoardResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public BoardResponseDto(Board entity) {
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
