package org.mddjinhoon.demo.base1.web.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BoardUpdateRequestDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    @Builder
    public BoardUpdateRequestDto(Long id, String title, String content, String author){
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
