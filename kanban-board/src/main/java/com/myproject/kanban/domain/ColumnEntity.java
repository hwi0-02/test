package com.myproject.kanban.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "COLUMN_ENTITY")
public class ColumnEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "column_seq_generator")
    @SequenceGenerator(name = "column_seq_generator", sequenceName = "COLUMN_SEQ", allocationSize = 1)
    private Long id;

    private String title;

    private Integer position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToMany(mappedBy = "column", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("position ASC")
    private List<Card> cards = new ArrayList<>();
}
