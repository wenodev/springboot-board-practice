package com.weno.boardpractice.service;

import com.weno.boardpractice.BoardPracticeApplicationTests;
import com.weno.boardpractice.domain.entity.BoardEntity;
import com.weno.boardpractice.domain.entity.MemberEntity;
import com.weno.boardpractice.domain.repository.BoardRepository;
import com.weno.boardpractice.domain.repository.MemberRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.Optional;

import static org.junit.Assert.*;

public class BoardServiceTest extends BoardPracticeApplicationTests {

    @Autowired
    BoardRepository boardRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void create(){


        Optional<MemberEntity> memberEntity = memberRepository.findById(1L);

        BoardEntity board = BoardEntity.builder()
                .title("t1")
                .content("c1")
                .writer("user1")
                .memberEntity(memberEntity.get())
                .build();


        boardRepository.save(board);
        System.out.println("board : " + board);


    }

}