package ru.project.board.factory;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;

public class RandomBoardFactoryWithCellAmount extends AbstractBoardFactory{
    public RandomBoardFactoryWithCellAmount(int rows, int cols) throws BoardFactoryException {
        super(rows, cols);
    }

    @Override
    public Board createBoard() throws BoardValidationException {
        return null;
    }
}
