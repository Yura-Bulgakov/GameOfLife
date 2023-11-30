package ru.project.board.factory;

public abstract class AbstractBoardFactory implements BoardFactory {
    protected int rows;
    protected int cols;

    public AbstractBoardFactory(int rows, int cols) throws BoardFactoryException {
        if (rows < 1 || cols < 1){
            throw new BoardFactoryException(
                    String.format("Количество строк %d и столбцов %d должно быть положительным",rows,cols));
        }
        this.rows = rows;
        this.cols = cols;
    }

    public void setRows(int rows) throws BoardFactoryException {
        if (rows < 1 ){
            throw new BoardFactoryException("Количество строк " + rows + " неположительное");
        }
        this.rows = rows;
    }

    public void setCols(int cols) throws BoardFactoryException {
        if (cols < 1){
            throw new BoardFactoryException("Количество столбцов " + cols + " неположительное");
        }
        this.cols = cols;
    }
}
