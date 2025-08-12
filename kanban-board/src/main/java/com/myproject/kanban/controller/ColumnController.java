package com.myproject.kanban.controller;

import com.myproject.kanban.dto.ColumnDto;
import com.myproject.kanban.dto.CreateColumnRequestDto;
import com.myproject.kanban.service.ColumnService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ColumnController {

    private final ColumnService columnService;

    @PostMapping("/boards/{boardId}/columns")
    public ResponseEntity<ColumnDto> createColumn(@PathVariable Long boardId, @RequestBody CreateColumnRequestDto requestDto) {
        ColumnDto createdColumn = columnService.createColumn(boardId, requestDto);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath().path("/api/columns/{id}")
                .buildAndExpand(createdColumn.getId())
                .toUri();

        return ResponseEntity.created(location).body(createdColumn);
    }

    @PatchMapping("/columns/{columnId}/move")
    public ResponseEntity<Void> moveColumn(@PathVariable Long columnId, @RequestBody com.myproject.kanban.dto.ColumnMoveRequestDto requestDto) {
        columnService.moveColumn(columnId, requestDto);
        return ResponseEntity.ok().build();
    }
}
