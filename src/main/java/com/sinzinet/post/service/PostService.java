package com.sinzinet.post.service;

import com.sinzinet.post.dto.PostResponseDto;
import com.sinzinet.post.entity.Post;
import com.sinzinet.post.entity.PostTag;
import com.sinzinet.post.mapper.PostMapper;
import com.sinzinet.post.repository.PostRepository;
import com.sinzinet.post.repository.PostTagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final PostTagRepository postTagRepository;

    public PostService(PostRepository postRepository, PostMapper postMapper, PostTagRepository postTagRepository) {
        this.postRepository = postRepository;
        this.postMapper = postMapper;
        this.postTagRepository = postTagRepository;
    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        return postMapper.postToPostResponseDtos(posts);
    }

    public Post getPostById(long postNo) {
        return postRepository.findById(postNo);
    }

    public Post createPost(Post post) {

        return postRepository.save(post);
    }

    public void updatePost(Post post) {
        postRepository.save(post);
    }

    //post 삭제 동시에 post_tag도 삭제
    @Transactional
    public void deletePostById(Long postNo) {
        Optional<Post> post = postRepository.findById(postNo);

        if (post.isPresent()) {
            List<PostTag> postTags = post.get().getPostTags();
            for (PostTag postTag : postTags) {
                postTagRepository.delete(postTag);
            }

            postRepository.delete(post.get());
        }
    }

}
