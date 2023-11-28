package ru.project.board.factory;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;

public interface BoardFactoryFactory {

    BoardFactory createBoardFactory() throws BoardFactoryException;
}
