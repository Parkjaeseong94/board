package com.sinzinet.board.repository;

import com.sinzinet.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, String> {
    Optional<Board> findByBoardCd(String boardCd);

    void deleteByBoardCd(String boardCd);

}