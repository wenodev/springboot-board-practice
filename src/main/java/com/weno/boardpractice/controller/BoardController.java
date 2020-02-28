package com.weno.boardpractice.controller;

import com.weno.boardpractice.dto.BoardDto;
import com.weno.boardpractice.service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@AllArgsConstructor
public class BoardController {

    private BoardService boardService;

    @GetMapping("/")
    public String list(Model model){
        List<BoardDto> boardDtoList = boardService.getBoardlist();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth",auth);

        model.addAttribute("boardList", boardDtoList);
        return "/board/list.html";
    }

    @GetMapping("/post/{no}")
    public String detail(@PathVariable("no") Long no, Model model, Principal principal){
        BoardDto boardDto = boardService.getPost(no);


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("auth",auth);

        model.addAttribute("boardDto", boardDto);
        return "/board/detail.html";
    }

    @GetMapping("/post")
    public String write(Principal principal){
        System.out.println(principal.getName());
        return "/board/write.html";
    }

    @GetMapping("/post/edit/{no}")
    public String edit(@PathVariable("no") Long no, Model model){
        BoardDto boardDto = boardService.getPost(no);
        model.addAttribute("boardDto", boardDto);

        return "/board/update.html";
    }



    @DeleteMapping("/post/{no}")
    public String delete(@PathVariable("no") Long no) {
        boardService.deletePost(no);

        return "redirect:/";
    }

    @PostMapping("/post")
    public String write(BoardDto boardDto, Principal principal ){
        boardService.savePost(boardDto, principal.getName());
        return "redirect:/";
    }

    @PutMapping("/post/edit/{no}")
    public String update(BoardDto boardDto){
        boardService.savePost(boardDto, null);

        return "redirect:/";
    }


}
