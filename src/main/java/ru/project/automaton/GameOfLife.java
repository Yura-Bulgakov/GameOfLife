package ru.project.automaton;

import ru.project.board.Board;
import ru.project.board.validation.BoardValidationException;

public class GameOfLife implements CellularAutomaton {
    @Override
    public Board makeMove(boolean[][] board) throws AutomationException {
        int rows = board.length;
        int cols = board[0].length;
        boolean[][] newBoard = new boolean[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countLiveNeighbors(board, i, j);
                if (board[i][j]) {
                    // Живая клетка
                    newBoard[i][j] = neighbors == 2 || neighbors == 3;
                } else {
                    // Мёртвая клетка
                    newBoard[i][j] = neighbors == 3;
                }
            }
        }
        return new Board(newBoard);
    }

    private static int countLiveNeighbors(boolean[][] board, int row, int col) {
        int count = 0;
        int rows = board.length;
        int cols = board[0].length;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                int r = (i + rows) % rows; // Обработка перехода через верхний и нижний края
                int c = (j + cols) % cols; // Обработка перехода через левый и правый края

                if (!(i == row && j == col) && board[r][c]) {
                    count++;
                }
            }
        }
        return count;
    }


}
