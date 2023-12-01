package com.sinzinet.post.entity;

import com.sinzinet.board.entity.Board;
import com.sinzinet.tag.entity.Tag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "POST_TAG")
public class PostTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postTagId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "POST_NO")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "BOARD_CD")
    private Board board;

    @ManyToOne
    @JoinColumn(name = "TAG_NO")
    private Tag tag;

    public void addPost(Post post) {
        this.post = post;
        if (!this.post.getPostTags().contains(this)) {
            this.post.getPostTags().add(this);
        }
    }

    public void addBoard(Board board){
        this.board = board;
    }

    public void addTag(Tag tag) {
        this.tag = tag;
        if (!this.tag.getPostTags().contains(this)) {
            this.tag.addPostTag(this);
        }
    }
}
