package ru.project.board.factory;

public abstract class AbstractBoardFactory implements BoardFactory {
    protected int rows;
    protected int cols;

    public AbstractBoardFactory(int rows, int cols) throws BoardFactoryException {
        if (rows < 1 || cols < 1){
            throw new BoardFactoryException("Количество строк " + rows + ", и столбцов " + cols + " неправильное");
        }
        this.rows = rows;
        this.cols = cols;
    }

    public void setRows(int rows) throws BoardFactoryException {
        if (rows < 1 ){
            throw new BoardFactoryException("Количество строк " + rows + " неправильное");
        }
        this.rows = rows;
    }

    public void setCols(int cols) throws BoardFactoryException {
        if (cols < 1){
            throw new BoardFactoryException("Количество столбцов " + cols + " неправильное");
        }
        this.cols = cols;
    }
}
