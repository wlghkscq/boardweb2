package com.example.boardweb2.service;

// 글쓰기 Form에서 내용을 입력한 뒤, ‘글쓰기’ 버튼을 누르면 Post 형식으로 요청이 오고, BoardService의 savePost()를 실행하게 됩니다.

import com.example.boardweb2.domain.entity.Board;
import com.example.boardweb2.domain.repository.BoardRepository;
import com.example.boardweb2.dto.BoardDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {

        this.boardRepository = boardRepository;
    }


    // 게시글 등록
    @Transactional
    public Long savePost(BoardDto boardDto) {

        return boardRepository.save(boardDto.toEntity()).getId();
    }




    // 게시판 전체 목록 보여주기
    @Transactional
    public List<BoardDto> getBoardList(Pageable pageable) {
        Page<Board> boardList = boardRepository.findAll(pageable);
        List<BoardDto> boardDtoList = new ArrayList<>();

        for (Board board : boardList) {
            BoardDto boardDto = BoardDto.builder()
                    .id(board.getId())
                    .author(board.getAuthor())
                    .title(board.getTitle())
                    .content(board.getContent())
                    .createDate(board.getCreateDate())
                    .modifiedDate(board.getModifiedDate())
                    .build();
            boardDtoList.add(boardDto);
        }
        return boardDtoList;
    }




    // 해당게시글 상세페이지 보여주기
    @Transactional
    public BoardDto getPost(Long id) {
        Board board = boardRepository.findById(id).get();

        BoardDto boardDto = BoardDto.builder()
                .id(board.getId())
                .author(board.getAuthor())
                .title(board.getTitle())
                .content(board.getContent())
                .createDate(board.getCreateDate())
                .build();
        return boardDto;

    }

    // 해당 게시글 삭제
    @Transactional
    public void deletePost(Long id) {
        boardRepository.deleteById(id);
    }



}
