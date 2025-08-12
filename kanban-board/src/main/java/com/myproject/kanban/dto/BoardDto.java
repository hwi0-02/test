package com.myproject.kanban.dto;

import com.myproject.kanban.domain.Board;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private String title;
    private List<ColumnDto> columns;

    public static BoardDto from(Board board) {
        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setColumns(board.getColumns().stream().map(ColumnDto::from).collect(Collectors.toList()));
        return dto;
    }
}