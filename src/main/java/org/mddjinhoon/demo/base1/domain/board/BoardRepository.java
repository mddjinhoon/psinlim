package org.mddjinhoon.demo.base1.domain.board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
}
