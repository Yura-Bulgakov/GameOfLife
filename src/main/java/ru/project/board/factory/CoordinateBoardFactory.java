package ru.project.board.factory;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;
import ru.project.board.Coordinate;

public class CoordinateBoardFactory extends AbstractBoardFactory{
    private Coordinate[] coordinates;

    public CoordinateBoardFactory(int row, int col, Coordinate... coordinates) throws BoardFactoryException {
        super(row, col);
        checkCoordinatesFormat(coordinates);
        this.coordinates = coordinates;
    }

    public void setCoordinates(Coordinate[] coordinates) throws BoardFactoryException {
        checkCoordinatesFormat(coordinates);
        this.coordinates = coordinates;
    }
    private void checkCoordinatesFormat(Coordinate[] coordinates) throws BoardFactoryException{
        for (Coordinate coordinate: coordinates) {
            if (coordinate.getCol() > this.cols || coordinate.getRow() > this.rows ||
            coordinate.getCol() < 0 || coordinate.getRow() < 0){
                throw new BoardFactoryException("Неправильный формат координат. Координаты: Row "
                        + coordinate.getRow() +", Col " + coordinate.getCol() + " выходят за границы поля: Rows"
                        + this.rows + ", Cols " + this.cols);
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
