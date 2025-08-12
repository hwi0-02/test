package com.myproject.kanban.dto;

import com.myproject.kanban.domain.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    private Long id;
    private String title;
    private String description;
    private Integer position;
    private String author;

    public static CardDto from(Card card) {
        CardDto dto = new CardDto();
        dto.setId(card.getId());
        dto.setTitle(card.getTitle());
        dto.setDescription(card.getDescription());
        dto.setPosition(card.getPosition());
        dto.setAuthor(card.getAuthor());
        return dto;
    }
}
