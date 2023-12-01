package com.sinzinet.board.mapper;

import com.sinzinet.board.dto.BoardPostDto;
import com.sinzinet.board.dto.BoardResponseDto;
import com.sinzinet.board.entity.Board;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board boardPostDtoToBoard(BoardPostDto boardPostDto);
    BoardResponseDto boardToBoardResponseDto(Board board);

}
