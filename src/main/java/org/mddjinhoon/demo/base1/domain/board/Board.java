package org.mddjinhoon.demo.base1.domain.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.mddjinhoon.demo.base1.domain.BaseTimeEntity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@NoArgsConstructor // 기본생성자임 헷갈리지마셈.
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;

    private String title;

    private String content;

    private String author;

    @Builder
    public Board(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}