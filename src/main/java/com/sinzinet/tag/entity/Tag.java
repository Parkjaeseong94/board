package com.sinzinet.tag.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sinzinet.board.entity.Board;
import com.sinzinet.post.entity.PostTag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long tagNo;

    @Column(nullable = false)
    private String tag;

    @ManyToOne
    @JoinColumn(name = "board_cd", nullable = false, updatable = false)
    private Board board;

    @OneToMany(mappedBy = "tag")
    private List<PostTag> postTags = new ArrayList<>();

    public void addPostTag(PostTag postTag) {
        this.postTags.add(postTag);
        if (postTag.getTag() != this) {
            postTag.addTag(this);
        }
    }


}
