package ru.project.board;

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
        if (coordinates == null || coordinates.length < 1) {
            throw new BoardFactoryException("Пустой параметр координат");
        }
        for (Coordinate coordinate: coordinates) {
            if (coordinate.getCol() > this.col || coordinate.getRow() > this.row ||
            coordinate.getCol() < 0 || coordinate.getRow() < 0){
                throw new BoardFactoryException("Неправильный формат координат");
            }
        }
    }

    @Override
    public Board createBoard() {
        boolean[][] board = new boolean[this.row][this.col];
        for (Coordinate coordinate : coordinates) {
            board[coordinate.getRow()][coordinate.getCol()] = true;
        }
        return new Board(board);
    }
}
