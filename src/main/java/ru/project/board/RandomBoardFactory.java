package ru.project.board;

import java.util.Random;


public class RandomBoardFactory extends AbstractBoardFactory{

    public RandomBoardFactory(int row, int col) throws BoardFactoryException {
        super(row,col);
    }

    @Override
    public Board createBoard()  {
        boolean[][] board = new boolean[this.row][this.col];
        Random random = new Random();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board[i][j] = random.nextBoolean();
            }
        }
        return new Board(board);
    }
}
