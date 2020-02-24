package com.weno.boardpractice.domain.entity;


import lombok.*;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
@Entity
@Table(name = "board")
public class BoardEntity extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 10, nullable = true)
    private String writer;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    @ManyToOne
    private MemberEntity memberEntity;


    @Builder
    public BoardEntity(Long id, String writer,  String title, String content,  MemberEntity memberEntity ){
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.memberEntity = memberEntity;
    }

}
