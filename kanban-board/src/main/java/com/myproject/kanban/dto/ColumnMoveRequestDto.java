package com.myproject.kanban.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ColumnMoveRequestDto {
    private Integer newPosition;
}
