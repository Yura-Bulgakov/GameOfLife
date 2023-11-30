package ru.project.board.factory;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;
import ru.project.board.Coordinate;

public class CoordinateBoardFactory extends AbstractBoardFactory{
    private Coordinate[] coordinates;

    public CoordinateBoardFactory(int row, int col, Coordinate... coordinates) throws BoardFactoryException {
        super(row, col);
        checkCoordinates(coordinates);
        this.coordinates = coordinates;
    }

    public void setCoordinates(Coordinate[] coordinates) throws BoardFactoryException {
//        checkCoordinates(coordinates);
        this.coordinates = coordinates;
    }
    private void checkCoordinates(Coordinate[] coordinates) throws BoardFactoryException{
        for (Coordinate coordinate: coordinates) {
            if (coordinate.getCol() > this.cols || coordinate.getRow() > this.rows ||
            coordinate.getCol() < 0 || coordinate.getRow() < 0){
                throw new BoardFactoryException(String.format("Координата [%d,%d] выходит за границы поля размера %dx%d"
                        ,coordinate.getRow(),coordinate.getCol(),this.rows,this.cols));
            }
        }
    }

    @Override
    public Board createBoard() throws BoardValidationException {
        boolean[][] board = new boolean[this.rows][this.cols];
        if (coordinates != null){
            for (Coordinate coordinate : coordinates) {
                board[coordinate.getRow()][coordinate.getCol()] = true;
            }
        }
        return new Board(board);
    }
}
