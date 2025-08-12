package com.myproject.kanban.dto;

import com.myproject.kanban.domain.ColumnEntity;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class ColumnDto {
    private Long id;
    private String title;
    private Integer position;
    private List<CardDto> cards;

    public static ColumnDto from(ColumnEntity column) {
        ColumnDto dto = new ColumnDto();
        dto.setId(column.getId());
        dto.setTitle(column.getTitle());
        dto.setPosition(column.getPosition());
        dto.setCards(column.getCards().stream().map(CardDto::from).collect(Collectors.toList()));
        return dto;
    }
}
