package com.sinzinet.board.service;

import com.sinzinet.board.entity.Board;
import com.sinzinet.board.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    @Autowired
    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    public Optional<Board> getBoardByCode(String boardCode) {
        return boardRepository.findById(boardCode);
    }

    public Board createBoard(Board board) {
        return boardRepository.save(board);
    }

    public void updateBoard(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoardByCode(String boardCode) {
        boardRepository.deleteByBoardCd(boardCode);
    }
}
