package com.sinzinet.tag.mapper;

import com.sinzinet.board.dto.BoardPostDto;
import com.sinzinet.board.entity.Board;
import com.sinzinet.tag.dto.TagPostDto;
import com.sinzinet.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mapping(source = "boardCd", target = "board.boardCd")
    Tag tagPostDtoToTag(TagPostDto tagPostDto);

}
