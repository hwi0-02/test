package com.myproject.kanban.service;

import com.myproject.kanban.domain.Board;
import com.myproject.kanban.dto.BoardDto;
import com.myproject.kanban.dto.CreateBoardRequestDto;
import com.myproject.kanban.repository.BoardRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public BoardDto createBoard(CreateBoardRequestDto requestDto) {
        Board board = new Board();
        board.setTitle(requestDto.getTitle());
        Board savedBoard = boardRepository.save(board);
        // In a real scenario, you might want to return a DTO that doesn't contain the full list of columns for a create response
        return BoardDto.from(savedBoard);
    }

    @Transactional(readOnly = true)
    public BoardDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));
        return BoardDto.from(board);
    }

    @Transactional(readOnly = true)
    public java.util.List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardDto::from)
                .collect(java.util.stream.Collectors.toList());
    }
}