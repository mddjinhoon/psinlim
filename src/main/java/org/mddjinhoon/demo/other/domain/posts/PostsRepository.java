package org.mddjinhoon.demo.other.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostsRepository extends JpaRepository<Posts, Long> {
}
/*
* DB Layer 접근자로, JPA에서는 Repository라고 부른다.
* SQL Mapper(Mybatis, Ibatis)에서는 Dao라고 불렀다.
* Interface로 생성 후 상속받아주면 기본 crud 생성됨.
* */