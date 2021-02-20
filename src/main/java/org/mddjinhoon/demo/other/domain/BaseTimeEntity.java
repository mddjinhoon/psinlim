package org.mddjinhoon.demo.other.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass //해당 어노테이션이 적용되어 있는 엔티티를 상속할 경우, 그 엔티티에 존재하는 필드들도 자동으로 컬름으로 인식한다.
@EntityListeners(AuditingEntityListener.class) // 어노테이션 인자에 들어있는 클래스 기능을 포함시킨다.
public class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}