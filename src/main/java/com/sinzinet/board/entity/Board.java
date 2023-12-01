package com.sinzinet.board.entity;

import com.sinzinet.post.entity.PostTag;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "BOARD")
public class Board {

    @Id
    private String boardCd;

    @Column(nullable = true)
    private String boardNm;

    public Board(String boardCd) {
        this.boardCd = boardCd;
    }

}
