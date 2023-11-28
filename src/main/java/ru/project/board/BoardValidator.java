package ru.project.board;

import ru.project.automaton.AutomationException;

public class BoardValidator {
    public static void validateBoard(boolean[][] board) throws  BoardValidationException {
        if (board == null || board.length == 0 || board[0] == null) {
            throw new BoardValidationException("Input board is null");
        }
        int rows = board.length;
        int cols = board[0].length;
        if (cols == 0) {
            throw new BoardValidationException("Input board has zero columns");
        }
        for (int i = 1; i < rows; i++) {
            if (board[i] == null) {
                throw new BoardValidationException("Row " + i + " is null");
            }

            if (board[i].length != cols) {
                throw new BoardValidationException("Input board has inconsistent number of columns at row " + i);
            }
        }
    }
}
