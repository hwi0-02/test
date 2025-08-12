package com.myproject.kanban.service;

import com.myproject.kanban.domain.Board;
import com.myproject.kanban.domain.ColumnEntity;
import com.myproject.kanban.dto.ColumnDto;
import com.myproject.kanban.dto.CreateColumnRequestDto;
import com.myproject.kanban.repository.BoardRepository;
import com.myproject.kanban.repository.ColumnRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ColumnService {

    private final BoardRepository boardRepository;
    private final ColumnRepository columnRepository;

    @Transactional
    public ColumnDto createColumn(Long boardId, CreateColumnRequestDto requestDto) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new EntityNotFoundException("Board not found with id: " + boardId));

        ColumnEntity column = new ColumnEntity();
        column.setTitle(requestDto.getTitle());
        column.setBoard(board);

        // Set the position for the new column to be the last one
        int position = board.getColumns().size();
        column.setPosition(position);

        ColumnEntity savedColumn = columnRepository.save(column);

        // Note: The board.getColumns() list might not be updated in the in-memory Board object after saving the column.
        // It's safer to re-fetch the board or manage the relationship manually if immediate consistency is needed.
        // For the purpose of returning the new column's DTO, this is sufficient.
        return ColumnDto.from(savedColumn);
    }

    @Transactional
    public void moveColumn(Long columnId, com.myproject.kanban.dto.ColumnMoveRequestDto requestDto) {
        ColumnEntity columnToMove = columnRepository.findById(columnId)
                .orElseThrow(() -> new EntityNotFoundException("Column not found with id: " + columnId));

        Board board = columnToMove.getBoard();
        java.util.List<ColumnEntity> columns = board.getColumns(); // @OrderBy("position ASC") ensures this list is sorted

        int oldPosition = columnToMove.getPosition();
        int newPosition = requestDto.getNewPosition();

        if (newPosition < 0 || newPosition >= columns.size()) {
            throw new IllegalArgumentException("Invalid new position: " + newPosition);
        }

        // Remove the column and insert it at the new position
        columns.remove(oldPosition);
        columns.add(newPosition, columnToMove);

        // Update positions of all columns in the list
        for (int i = 0; i < columns.size(); i++) {
            columns.get(i).setPosition(i);
        }

        columnRepository.saveAll(columns);
    }
}
