package com.example.boardweb2.dto;

// Controller와 Service 사이에서 데이터를 주고받는 DTO(Data Access Object)를 구현


import com.example.boardweb2.domain.entity.Board;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardDto {

    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDateTime createDate;
    private LocalDateTime modifiedDate;


    //DTO에서 필요한 부분을 빌더 패턴을 통해 Entity로 만드는 일
    public Board toEntity(){
        Board build = Board.builder()
                .id(id)
                .author(author)
                .title(title)
                .content(content)
                .build();
        return build;
    }

    @Builder
    public BoardDto(Long id, String author, String title, String content, LocalDateTime createDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
    }
}
