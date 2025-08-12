package com.myproject.kanban.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCardRequestDto {
    private String title;
    private String description;
    private String author;
}
