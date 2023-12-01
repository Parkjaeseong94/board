package com.sinzinet.post.controller;

import com.sinzinet.post.dto.PostPostDto;
import com.sinzinet.post.dto.PostResponseDto;
import com.sinzinet.post.entity.Post;
import com.sinzinet.post.mapper.PostMapper;
import com.sinzinet.post.service.PostService;
import com.sinzinet.utils.UriCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final static String POST_DEFAULT_URL = "/posts";
    private final PostService postService;
    private final PostMapper postMapper;


    @Autowired
    public PostController(PostService postService, PostMapper postMapper) {
        this.postService = postService;
        this.postMapper = postMapper;
    }

    //글쓰기
    @PostMapping
    public ResponseEntity createPost(@RequestBody PostPostDto postDto) {

        Post post = postService.createPost(postMapper.postPostDtoToPost(postDto));

        URI location = UriCreator.createUri(POST_DEFAULT_URL, post.getPostNo());

        return ResponseEntity.created(location).build();
    }

    //Post 전체 조회
    @GetMapping
    public ResponseEntity getAllposts() {

        List<PostResponseDto> response = postService.getAllPosts();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //PostNo로 조회
    @GetMapping("/{post-no}")
    public ResponseEntity getPost(@PathVariable("post-no") long postNo) {
        Post post = postService.getPostById(postNo);

        PostResponseDto responseDto = postMapper.postToPostResponseDto(post);

        return new ResponseEntity<>(responseDto , HttpStatus.OK);
    }


//    수정 미구현
//    @PatchMapping("/{postNo}")
//    public void updatePost(@PathVariable long postNo, @RequestBody Post post) {
//        postService.updatePost(post);
//    }


    @DeleteMapping("/{post-no}")
    public ResponseEntity deletePost(@PathVariable("post-no") long postNo) {
        postService.deletePostById(postNo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
