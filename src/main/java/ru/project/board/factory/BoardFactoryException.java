package ru.project.board.factory;

public class BoardFactoryException extends Exception{
    public BoardFactoryException(String message) {
        super(message);
    }

    public BoardFactoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardFactoryException(Throwable cause) {
        super(cause);
    }
}
