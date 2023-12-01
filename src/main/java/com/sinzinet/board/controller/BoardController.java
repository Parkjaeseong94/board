package com.sinzinet.board.controller;

import com.sinzinet.board.dto.BoardPostDto;
import com.sinzinet.board.entity.Board;
import com.sinzinet.board.mapper.BoardMapper;
import com.sinzinet.board.service.BoardService;
import com.sinzinet.utils.UriCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/boards")
public class BoardController {

    private final static String BOARD_DEFAULT_URL = "/boards";
    private final BoardService boardService;
    private final BoardMapper boardMapper;

    @Autowired
    public BoardController(BoardService boardService, BoardMapper boardMapper) {
        this.boardService = boardService;
        this.boardMapper = boardMapper;
    }

    @GetMapping
    public ResponseEntity getAllBoards() {
        List<Board> response = boardService.getAllBoards();


        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{boardCode}")
    public Optional<Board> getBoardByCode(@PathVariable String boardCode) {
        return boardService.getBoardByCode(boardCode);
    }

    @PostMapping
    public ResponseEntity createBoard(@RequestBody BoardPostDto boardDto) {

        Board board = boardService.createBoard(boardMapper.boardPostDtoToBoard(boardDto));
        URI location = UriCreator.createUri(BOARD_DEFAULT_URL, board.getBoardCd());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{boardCode}")
    public void updateBoard(@PathVariable String boardCode, @RequestBody Board board) {
        boardService.updateBoard(board);
    }

    @DeleteMapping("/{boardCode}")
    public void deleteBoard(@PathVariable String boardCode) {
        boardService.deleteBoardByCode(boardCode);
    }
}