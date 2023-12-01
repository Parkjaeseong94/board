package com.sinzinet.post.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PostResponseDto {

    private int postNo;

    private String postSj;

    private String postCn;

    private String regstrId;

    private String boardCd;

}
