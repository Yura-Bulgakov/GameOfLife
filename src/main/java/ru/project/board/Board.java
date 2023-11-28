package ru.project.board;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Getter
@Slf4j
public class Board {
    private final boolean[][] board;

    public Board(boolean[][] board) {
        try {
            BoardValidator.validateBoard(board);
        } catch (BoardValidationException e) {
            log.error(e.getMessage());
        }
        this.board = board;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board1 = (Board) o;
        return Arrays.deepEquals(board, board1.board);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(board);
    }
}
