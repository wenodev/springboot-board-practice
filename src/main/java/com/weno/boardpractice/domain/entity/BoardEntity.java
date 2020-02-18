package com.weno.boardpractice.domain.entity;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
@Table(name="board")
public class BoardEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    // OrderGroup N : 1 User
    @ManyToOne
    private MemberEntity memberEntity;

    @Builder
    public BoardEntity(Long id,  String title, String content, MemberEntity memberEntity){
        this.id = id;
        this.title = title;
        this.content = content;
        this.memberEntity = memberEntity;
    }

}
