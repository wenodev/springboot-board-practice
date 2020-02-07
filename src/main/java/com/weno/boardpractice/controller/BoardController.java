package com.weno.boardpractice.controller;


import com.weno.boardpractice.dto.BoardDto;
import com.weno.boardpractice.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @GetMapping("/")
    public String list(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardlist();
        model.addAttribute("boardList", boardDtoList);
        return "/board/list.html";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);
        model.addAttribute("boardDto", boardDto);
        return "/board/detail.html";
    }

    @GetMapping("/post")
    public String write(){
        return "/board/write.html";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);

        model.addAttribute("boardDto", boardDto);
        return "/board/update.html";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDto){
        boardService.savePost(boardDto);

        return "redirect:/";
    }

    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }



    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);

        return "redirect:/";
    }



}
