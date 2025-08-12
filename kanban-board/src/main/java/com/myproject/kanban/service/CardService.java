package com.myproject.kanban.service;

import com.myproject.kanban.domain.Card;
import com.myproject.kanban.domain.ColumnEntity;
import com.myproject.kanban.dto.CardDto;
import com.myproject.kanban.dto.CreateCardRequestDto;
import com.myproject.kanban.repository.CardRepository;
import com.myproject.kanban.repository.ColumnRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final ColumnRepository columnRepository;
    private final CardRepository cardRepository;

    @Transactional
    public CardDto createCard(Long columnId, CreateCardRequestDto requestDto) {
        ColumnEntity column = columnRepository.findById(columnId)
                .orElseThrow(() -> new EntityNotFoundException("Column not found with id: " + columnId));

        Card card = new Card();
        card.setTitle(requestDto.getTitle());
        card.setDescription(requestDto.getDescription());
        card.setAuthor(requestDto.getAuthor());
        card.setColumn(column);

        // Set the position for the new card to be the last one
        int position = column.getCards().size();
        card.setPosition(position);

        Card savedCard = cardRepository.save(card);

        return CardDto.from(savedCard);
    }

    @Transactional
    public void moveCard(Long cardId, com.myproject.kanban.dto.CardMoveRequestDto requestDto) {
        Card cardToMove = cardRepository.findById(cardId)
                .orElseThrow(() -> new EntityNotFoundException("Card not found with id: " + cardId));

        ColumnEntity column = cardToMove.getColumn();
        java.util.List<Card> cards = column.getCards(); // @OrderBy("position ASC") ensures this list is sorted

        int oldPosition = cardToMove.getPosition();
        int newPosition = requestDto.getNewPosition();

        if (newPosition < 0 || newPosition >= cards.size()) {
            throw new IllegalArgumentException("Invalid new position: " + newPosition);
        }

        // Remove the card and insert it at the new position
        cards.remove(oldPosition);
        cards.add(newPosition, cardToMove);

        // Update positions of all cards in the list
        for (int i = 0; i < cards.size(); i++) {
            cards.get(i).setPosition(i);
        }

        cardRepository.saveAll(cards);
    }
}
