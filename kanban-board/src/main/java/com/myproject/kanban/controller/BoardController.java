package com.myproject.kanban.controller;

import com.myproject.kanban.dto.BoardDto;
import com.myproject.kanban.dto.CreateBoardRequestDto;
import com.myproject.kanban.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping
    public ResponseEntity<BoardDto> createBoard(@RequestBody CreateBoardRequestDto requestDto) {
        BoardDto createdBoard = boardService.createBoard(requestDto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdBoard.getId())
                .toUri();
        return ResponseEntity.created(location).body(createdBoard);
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardDto> getBoard(@PathVariable Long boardId) {
        BoardDto boardDto = boardService.getBoard(boardId);
        return ResponseEntity.ok(boardDto);
    }

    @GetMapping
    public ResponseEntity<java.util.List<BoardDto>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }
    
}