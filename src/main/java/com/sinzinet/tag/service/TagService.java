package com.sinzinet.tag.service;

import com.sinzinet.tag.entity.Tag;
import com.sinzinet.tag.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> getAlltags() {
        return tagRepository.findAll();
    }

    public Optional<Tag> getTagById(long tagNo) {
        return tagRepository.findById(tagNo);
    }

    public Tag createTag(Tag tag) {
        return tagRepository.save(tag);
    }

    public void updateTag(Tag tag) {
        tagRepository.save(tag);
    }

    public void deleteTagById(long tagNo) {
        tagRepository.deleteById(tagNo);
    }
}
