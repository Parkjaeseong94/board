package com.sinzinet.post.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class PostPostDto {

    private String postSj;

    private String postCn;

    private String regstrId;

    private String boardCd;

    private List<PostTagDto> postTags;

}
