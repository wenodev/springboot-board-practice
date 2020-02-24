package com.weno.boardpractice.service;

import com.weno.boardpractice.domain.entity.BoardEntity;
import com.weno.boardpractice.domain.entity.MemberEntity;
import com.weno.boardpractice.domain.repository.BoardRepository;
import com.weno.boardpractice.domain.repository.MemberRepository;
import com.weno.boardpractice.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    private MemberRepository memberRepository;


    @Transactional
    public Long savePost(BoardDto boardDto) {

        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDto> getBoardlist() {

        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(BoardEntity boardEntity : boardEntityList){
            BoardDto boardDto = BoardDto.builder()
                    .id(boardEntity.getId())
                    .writer(boardEntity.getWriter())
                    .title(boardEntity.getTitle())
                    .content(boardEntity.getContent())
                    .createdDate(boardEntity.getCreatedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }

    @Transactional
    public BoardDto getPost(Long id) {
        Optional<BoardEntity> boardEntityWrapper = boardRepository.findById(id);
        BoardEntity boardEntity = boardEntityWrapper.get();

        BoardDto boardDTO = BoardDto.builder()
                .id(boardEntity.getId())
                .writer(boardEntity.getWriter())
                .title(boardEntity.getTitle())
                .content(boardEntity.getContent())
                .createdDate(boardEntity.getCreatedDate())
                .build();

        return boardDTO;
    }

    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }
}
