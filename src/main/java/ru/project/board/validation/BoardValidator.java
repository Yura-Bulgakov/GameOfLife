package ru.project.board.validation;

public class BoardValidator {
    public static void validateBoard(boolean[][] board) throws  BoardValidationException {
        if (board == null || board.length == 0 || board[0] == null) {
            throw new BoardValidationException("Board is null");
        }
        int rows = board.length;
        int cols = board[0].length;
        if (cols == 0) {
            throw new BoardValidationException("Board has zero columns");
        }
        for (int i = 1; i < rows; i++) {
            if (board[i] == null) {
                throw new BoardValidationException("Row " + i + " is null");
            }

            if (board[i].length != cols) {
                throw new BoardValidationException(String.format("Board has %d columns, expected %d columns", i, cols));
            }
        }
    }
}
