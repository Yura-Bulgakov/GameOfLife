package ru.project.board.factory;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;

import java.util.Random;


public class RandomBoardFactory extends AbstractBoardFactory{



    private int cellCount;

    public RandomBoardFactory(int row, int col, int count) throws BoardFactoryException {
        super(row,col);
        checkCount(count);
        this.cellCount = count;
    }

    public RandomBoardFactory(int row, int col) throws BoardFactoryException {
        this(row,col,-1);
    }

    public void setCellCount(int cellCount) throws BoardFactoryException {
        checkCount(cellCount);
        this.cellCount = cellCount;
    }

    @Override
    public Board createBoard() throws BoardValidationException {
        return this.cellCount > 0 ? createRandomBoardWithCellAmount() : createRandomBoard();
    }

    private Board createRandomBoard(){
        boolean[][] board = new boolean[this.rows][this.cols];
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                board[i][j] = random.nextBoolean();
            }
        }
        return new Board(board);
    }

    private Board createRandomBoardWithCellAmount(){
        boolean[][] board = new boolean[this.rows][this.cols];
        Random random = new Random();
        for (int count = 0; count < this.cellCount;) {
            int row = random.nextInt(this.rows);
            int col = random.nextInt(this.cols);
            if (!board[row][col]){
                board[row][col] = true;
                count++;
            }
        }
        return new Board(board);
    }

    private void checkCount(int count) throws BoardFactoryException {
        if (count > 0 && count > this.rows * this.cols){
            throw new BoardFactoryException(String.format("Невозможно задать %d живых клеток для поля размера %dx%d",
                    count, this.rows, this.cols));
        }
    }


}
