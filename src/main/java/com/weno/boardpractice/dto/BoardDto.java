package com.weno.boardpractice.dto;


import com.weno.boardpractice.domain.entity.BoardEntity;

import com.weno.boardpractice.domain.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;


@Data
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private MemberEntity memberEntity;

    public BoardEntity toEntity() {
        BoardEntity build = BoardEntity.builder()
                .id(id)
                .writer(writer)
                .title(title)
                .content(content)
                .memberEntity(memberEntity)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String writer, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate,  MemberEntity memberEntity) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.memberEntity = memberEntity;
    }

}
