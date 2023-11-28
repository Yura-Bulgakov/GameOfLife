package ru.project.board;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import ru.project.board.validation.BoardValidationException;
import ru.project.board.validation.BoardValidator;


@Getter
@EqualsAndHashCode
public class Board {
    private final boolean[][] board;

    public Board(boolean[][] board) throws BoardValidationException {
        BoardValidator.validateBoard(board);
        this.board = board;
    }
}
