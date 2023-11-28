package ru.project.board.validation;

public class BoardValidationException extends RuntimeException{
    public BoardValidationException(String message) {
        super(message);
    }

    public BoardValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BoardValidationException(Throwable cause) {
        super(cause);
    }
}
