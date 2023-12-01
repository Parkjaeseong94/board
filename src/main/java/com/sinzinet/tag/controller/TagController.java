package com.sinzinet.tag.controller;

import com.sinzinet.post.dto.PostPostDto;
import com.sinzinet.post.entity.Post;
import com.sinzinet.tag.dto.TagPostDto;
import com.sinzinet.tag.entity.Tag;
import com.sinzinet.tag.mapper.TagMapper;
import com.sinzinet.tag.repository.TagRepository;
import com.sinzinet.tag.service.TagService;
import com.sinzinet.utils.UriCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tags")
public class TagController {
    private final static String TAG_DEFAULT_URL = "/posts";
    private final TagService tagService;
    private final TagMapper tagMapper;

    public TagController(TagService tagService, TagMapper tagMapper) {
        this.tagService = tagService;
        this.tagMapper = tagMapper;
    }

    @Autowired

    @GetMapping
    public List<Tag> getAllTags() { return tagService.getAlltags();}

    @GetMapping("/{tagNo}")
    public Optional<Tag> getTagById(@PathVariable long tagNo) {
        return tagService.getTagById(tagNo);
    }

    @PostMapping
    public ResponseEntity createTag(@RequestBody TagPostDto tagDto) {

        Tag tag = tagService.createTag(tagMapper.tagPostDtoToTag(tagDto));



        URI location = UriCreator.createUri(TAG_DEFAULT_URL, tag.getTagNo());

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{tagNo}")
    public void updateTag(@PathVariable long tagNo, @RequestBody Tag tag) {
        tagService.updateTag(tag);
    }

    @DeleteMapping("/{tagNo}")
    public void deleteTag(@PathVariable long tagNo) {
        tagService.deleteTagById(tagNo);
    }

}
