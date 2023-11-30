package ru.project.board;

public class BoardBuffer {
    private int bufferCapacity;
    private int writeIndex;
    private Board[] boards;


    public BoardBuffer(int bufferCapacity) {
        if (bufferCapacity < 1){
            throw new IllegalArgumentException("Размер буфера полей должен быть положительным");
        }
        this.bufferCapacity = bufferCapacity;
        this.boards = new Board[this.bufferCapacity];
        this.writeIndex = -1;
    }

    public void push(Board board){
        boards[++writeIndex % bufferCapacity] = board;
        writeIndex++;
    }

    public boolean has(Board searchBoard){
        for (Board board : boards) {
            if (board!=null && board.equals(searchBoard)) {
                return true;
            }
        }
        return false;
    }

}
