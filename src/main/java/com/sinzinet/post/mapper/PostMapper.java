package com.sinzinet.post.mapper;

import com.sinzinet.board.entity.Board;
import com.sinzinet.post.dto.PostPatchDto;
import com.sinzinet.post.dto.PostPostDto;
import com.sinzinet.post.dto.PostResponseDto;
import com.sinzinet.post.entity.Post;
import com.sinzinet.post.entity.PostTag;
import com.sinzinet.tag.entity.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface PostMapper {

    default Post postPostDtoToPost(PostPostDto postPostDto) {

        Post post = new Post();
        Board board = new Board();
        board.setBoardCd(postPostDto.getBoardCd());
        post.setBoard(board);
        post.setPostSj(postPostDto.getPostSj());
        post.setPostCn(postPostDto.getPostCn());
        post.setRegstrId(postPostDto.getRegstrId());


        List<PostTag> postTags = postPostDto.getPostTags().stream()
                .map(postTagDto -> {
                    PostTag postTag = new PostTag();
                    Tag tag = new Tag();
                    tag.setTagNo(postTagDto.getTagNo());
                    postTag.addBoard(board);
                    postTag.addTag(tag);
                    postTag.addPost(post);
                    return postTag;
                }).collect(Collectors.toList());

        post.setPostTags(postTags);


        return post;
    };


    Post postPatchDtoToPost(PostPatchDto postPatchDto);

    @Mapping(source = "board.boardCd" , target = "boardCd")
    PostResponseDto postToPostResponseDto(Post post);


    List<PostResponseDto> postToPostResponseDtos(List<Post> posts);
}