package ru.project.board.factory;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;

import java.util.Random;


public class RandomBoardFactory extends AbstractBoardFactory{

    public RandomBoardFactory(int row, int col) throws BoardFactoryException {
        super(row,col);
    }

    @Override
    public Board createBoard() throws BoardValidationException {
        boolean[][] board = new boolean[this.rows][this.cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = random.nextBoolean();
            }
        }
        return new Board(board);
    }
}
