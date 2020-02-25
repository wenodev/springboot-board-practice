package com.weno.boardpractice.service;

import com.weno.boardpractice.domain.entity.BoardEntity;
import com.weno.boardpractice.domain.entity.MemberEntity;
import com.weno.boardpractice.domain.repository.BoardRepository;
import com.weno.boardpractice.domain.repository.MemberRepository;
import com.weno.boardpractice.dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class BoardService {

    private BoardRepository boardRepository;

    private MemberRepository memberRepository;

    @Transactional
    public BoardEntity savePost(BoardDto boardDto, String email) {

        Optional<MemberEntity> memberEntity = memberRepository.findByEmail(email);

        BoardEntity board = BoardEntity.builder()
                .title(boardDto.getTitle())
                .content(boardDto.getContent())
                .writer(email)
                .memberEntity(memberEntity.get())
                .build();

        return boardRepository.save(board);
//        return boardRepository.save(boardDto.toEntity()).getId();
    }

    @Transactional
    public List<BoardDto> getBoardlist() {

        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDto> boardDtoList = new ArrayList<>();

        for(BoardEntity boardEntity : boardEntityList){
            BoardDto boardDto = BoardDto.builder()
                    .id(boardEntity.getId())
                    .writer(boardEntity.getMemberEntity().getEmail())
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
