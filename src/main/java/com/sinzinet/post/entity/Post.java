package com.sinzinet.post.entity;

import com.sinzinet.board.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long postNo;

    @Column
    private String postSj;

    @Column
    private String postCn;

    @Column
    private String regstrId;

    @Column(columnDefinition = "datetime")
    private LocalDateTime regDt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "BOARD_CD")
    private Board board;

    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<PostTag> postTags = new ArrayList<>();

    public List<PostTag> getPostTags() {
        return postTags;
    }
}
