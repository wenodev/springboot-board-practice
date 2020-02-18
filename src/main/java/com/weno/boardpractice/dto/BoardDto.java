package com.weno.boardpractice.dto;


import com.weno.boardpractice.domain.entity.BoardEntity;
import com.weno.boardpractice.domain.entity.MemberEntity;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private MemberEntity memberDto;

    public BoardEntity toEntity() {
        BoardEntity build = BoardEntity.builder()
                .id(id)
                .title(title)
                .content(content)
                .memberEntity(memberDto)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String title, String content, LocalDateTime createdDate, LocalDateTime modifiedDate, MemberEntity memberDto) {
        this.id = id;

        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.memberDto = memberDto;
    }

}
