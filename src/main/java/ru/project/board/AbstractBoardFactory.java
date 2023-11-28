package ru.project.board;

public abstract class AbstractBoardFactory implements BoardFactory {
    protected int row;
    protected int col;

    public AbstractBoardFactory(int row, int col) throws BoardFactoryException {
        if (row < 1 || col < 1){
            throw new BoardFactoryException("Количество строк " + row + ", и столбцов " + col + " неправильное");
        }
        this.row = row;
        this.col = col;
    }

    public void setRow(int row) throws BoardFactoryException {
        if (row < 1 ){
            throw new BoardFactoryException("Количество строк " + row + " неправильное");
        }
        this.row = row;
    }

    public void setCol(int col) throws BoardFactoryException {
        if (col < 1){
            throw new BoardFactoryException("Количество столбцов " + col + " неправильное");
        }
        this.col = col;
    }
}
