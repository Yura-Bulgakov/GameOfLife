package ru.project.board.factory;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;

public interface BoardFactory {
    Board createBoard() throws BoardValidationException;
}
