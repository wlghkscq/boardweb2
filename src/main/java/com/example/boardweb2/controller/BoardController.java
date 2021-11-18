package com.example.boardweb2.controller;

import com.example.boardweb2.domain.entity.Board;
import com.example.boardweb2.dto.BoardDto;
import com.example.boardweb2.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;


@Controller
public class BoardController {

    private  BoardService boardService;

    public BoardController(BoardService boardService){
        this.boardService = boardService;
    }




    @GetMapping("/")
    public String list(Model model,@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        List<BoardDto> boardDtoList = boardService.getBoardList(pageable);

        model.addAttribute("postList", boardDtoList);
//  boardDtoList를 board/list.html에 postList로 전달해 줍니다.
        return "board/list";
    }



    // 게시글 작성 페이지 보여주기
    @GetMapping("/post")
    public String post(){

        return "board/post";
    }

    // 게시글 작성 -> db 저장
    @PostMapping("/post")
    public String write(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    // 해당 id 게시글 상세페이지 보여주기
    @GetMapping("/post/{id}")
    public String detail(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/detail";
    }

    // 해당 id 게시글 수정 페이지 보여주기
    @GetMapping("/post/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model){
        BoardDto boardDto = boardService.getPost(id);
        model.addAttribute("post", boardDto);
        return "board/edit";
    }

    // 수정
    @PutMapping("/post/edit/{id}")
    public String update(BoardDto boardDto){
        boardService.savePost(boardDto);
        return "redirect:/";
    }

    // 삭제
    @DeleteMapping("/post/{id}")
    public String delete(@PathVariable("id") Long id){
        boardService.deletePost(id);
        return "redirect:/";
    }

}
