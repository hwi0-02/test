package com.myproject.kanban.controller;

import com.myproject.kanban.dto.CardDto;
import com.myproject.kanban.dto.CreateCardRequestDto;
import com.myproject.kanban.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;

    @PostMapping("/columns/{columnId}/cards")
    public ResponseEntity<CardDto> createCard(@PathVariable Long columnId, @RequestBody CreateCardRequestDto requestDto) {
        CardDto createdCard = cardService.createCard(columnId, requestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/cards/{id}")
                .buildAndExpand(createdCard.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdCard);
    }

    @PatchMapping("/cards/{cardId}/move")
    public ResponseEntity<Void> moveCard(@PathVariable Long cardId, @RequestBody com.myproject.kanban.dto.CardMoveRequestDto requestDto) {
        cardService.moveCard(cardId, requestDto);
        return ResponseEntity.ok().build();
    }
}
